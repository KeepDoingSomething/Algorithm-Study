import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public int solution(int k, int[] tangerine) {

        int answer = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for(int i : tangerine){
            map.put(i, map.getOrDefault(i, 0) +1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());

        list.sort((o1, o2) -> map.get(o2) - map.get(o1));

        for(int i :list) {
            k -= map.get(i);
            answer ++;
            if(k <=0) {
                break;
            }
        }


        return answer;
    }
}