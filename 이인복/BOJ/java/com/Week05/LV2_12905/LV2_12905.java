package com.Week05.LV2_12905;

public class LV2_12905 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(new int[][] {
                {0,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {0,0,1,0}
        }));
    }

    static class Solution {
        public int solution(int[][] board) {
            int ans = 0;
            int row = board.length;
            int col = board[0].length;
            int[][] dp = new int[row][col];

            for(int i = 0; i < row; i++) {
                for(int j = 0; j < col; j++) {
                    if(i == 0 || j == 0) {
                        dp[i][j] = board[i][j];
                    } else if(board[i][j] == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                    }

                    ans = Math.max(ans, dp[i][j]);
                }
            }

            return ans * ans;
        }
    }
}
