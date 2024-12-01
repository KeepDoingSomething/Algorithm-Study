package programmers.LV2_72412;

import java.util.*;
import java.util.stream.*;

public class Solution {

    Map<String, List<Integer>> search;
    final String EMPTY = "-";

    public int[] solution(String[] info, String[] query) {
        this.search = new HashMap<>();
        int[] result = new int[query.length];

        for(String user: info){
            putSearchKey(user);
        }

        for(String searchKey : search.keySet()){
            Collections.sort(search.get(searchKey));
        }

        for(int i = 0; i < query.length; i++){
            String[] queryInfo = query[i].split(" ");
            String queryKey = queryInfo[0] + queryInfo[2] + queryInfo[4] + queryInfo[6];
            int queryPoint = Integer.parseInt(queryInfo[7]);
            if(this.search.containsKey(queryKey)){
                List<Integer> pointList = this.search.get(queryKey);
                result[i] = binarySearch(pointList, queryPoint);
            }
        }

        return result;
    }

    private int binarySearch(List<Integer> pointList, int minPoint){
        int left = 0;
        int right = pointList.size() - 1;
        int result = 0;

        while(left <= right){
            int mid = (left + right) / 2;
            int checkPoint = pointList.get(mid);
            if(checkPoint >= minPoint){
                result = pointList.size() - mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return result;
    }

    private void putSearchKey(String user){
        String[] userInfo = user.split(" ");
        dfsKey(userInfo, new String[]{EMPTY, EMPTY, EMPTY, EMPTY}, -1);
    }

    private void dfsKey(String[] userInfo, String[] key, int depth){
        if(depth == 3){
            int userPoint = Integer.parseInt(userInfo[4]);
            String fullKey = String.join("", key);

            List<Integer> keyPoint = search.getOrDefault(fullKey, new ArrayList<Integer>());
            keyPoint.add(userPoint);
            search.put(fullKey, keyPoint);
            return;
        }

        depth += 1;
        key[depth] = userInfo[depth];
        dfsKey(userInfo, key, depth);
        key[depth] = EMPTY;
        dfsKey(userInfo, key, depth);
    }

}