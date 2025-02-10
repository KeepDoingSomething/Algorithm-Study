import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;

        int dp[][] = new int[height][height];
        int answer = 0;

        int max = Integer.MIN_VALUE;

        dp[0][0] = triangle[0][0];

        for(int i =1; i < height; i++){
            for(int j =0; j < triangle[i].length; j++){
                if(j==0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }
                else if(i == j){
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j],
                            dp[i-1][j] + triangle[i][j]);
                }
            }
        }

        for(int i = 0; i < triangle[height-1].length; i++){
            answer = Math.max(dp[height-1][i], answer);
        }
        return answer;
    }
}
