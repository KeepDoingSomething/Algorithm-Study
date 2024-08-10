package programmers.LV2_42860;

import java.util.*;

public class Solution {

    int moveCountResult;
    boolean[] visited;
    int[] targets;
    int targetSize;
    int nameLength;

    public int solution(String name) {

        char[] c = name.toCharArray();
        nameLength = name.length();
        List<Integer> targetList = new ArrayList();
        int updownCount = 0;

        for(int i = 0; i < c.length; i++){
            int case1 = c[i] - 'A';
            int case2 = 'Z' + 1 - c[i];
            int updown = Math.min(case1, case2);
            updownCount += updown;
            if(updown != 0){
                targetList.add(i);
            }else if(i == 0) {
                targetList.add(0);
            }
        }

        if(updownCount == 0) return 0;

        targetSize = targetList.size();
        if(targetSize == 1){
            return updownCount + Math.min(targetList.get(0), nameLength - targetList.get(0));
        }


        visited = new boolean[targetSize];
        targets = targetList.stream().mapToInt(Integer::intValue).toArray();
        moveCountResult = Integer.MAX_VALUE;
        dfs(0, 0, 1);

        System.out.println(Arrays.toString(targets));
        return moveCountResult + updownCount;
    }

    public void dfs(int curIdx, int moveCount, int depth){
        if(depth == targetSize){
            moveCountResult = Math.min(moveCount, moveCountResult);
            return;
        }
        visited[curIdx] = true;

        int leftIdx = 0;
        for(int i = 1; i < targetSize; i++){
            leftIdx = (curIdx - i) >= 0 ? (curIdx - i) : targetSize + (curIdx - i);
            if(!visited[leftIdx]) break;
        }

        int leftMoveCount = targets[curIdx] > targets[leftIdx]
                ? moveCount + targets[curIdx] - targets[leftIdx]
                : moveCount + targets[curIdx] + (nameLength - targets[leftIdx]);
        dfs(leftIdx, leftMoveCount, depth + 1);
        visited[leftIdx] = false;

        int rightIdx = 0;
        for(int i = 1; i < targetSize; i++){
            rightIdx = (curIdx + i) < targetSize ? (curIdx + i) : (curIdx + i) % targetSize;
            if(!visited[rightIdx]) break;
        }

        int rightMoveCount = targets[rightIdx] > targets[curIdx]
                ? moveCount + targets[rightIdx] - targets[curIdx]
                : moveCount + (nameLength - targets[curIdx]) + targets[rightIdx];
        dfs(rightIdx, rightMoveCount, depth + 1);
        visited[rightIdx] = false;
    }

}