package programmers.LV3_43105;

import java.util.*;

public class Solution {
    public int solution(int[][] triangle) {
        List<Integer>[] dp = new List[triangle.length];

        for(int i = 0; i < triangle.length; i++){
            dp[i] = new ArrayList<Integer>();
        }

        dp[0].add(triangle[0][0]);

        for(int i = 1; i < triangle.length; i++){
            List<Integer> curFloor = dp[i];
            List<Integer> beforeFloor = dp[i - 1];

            for(int j = 0; j < triangle[i].length; j++){
                int bestSum = 0;
                if(j == 0){
                    bestSum = triangle[i][j] + beforeFloor.get(j);
                }else if(j == triangle[i].length - 1){
                    bestSum = triangle[i][j] + beforeFloor.get(j - 1);
                }else{
                    bestSum = triangle[i][j] + Math.max(beforeFloor.get(j), beforeFloor.get(j - 1));
                }
                curFloor.add(bestSum);
            }
        }

        int answer = 0;
        List<Integer> lastFloor = dp[triangle.length - 1];
        for(int sumResult : lastFloor){
            answer = Math.max(answer, sumResult);
        }

        return answer;
    }
}