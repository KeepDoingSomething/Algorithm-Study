package com.Week20;

public class LeetCode_5 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.longestPalindrome("babad")
        );
    }

    static class Solution {
        public String longestPalindrome(String text) {
            int srt = 0, end = 0;
            int len = text.length();
            boolean[][] dp = new boolean[len][len];

            for(int s = len - 1; s >= 0; s--) {
                for(int e = s; e < len; e++) {
                    boolean srtEqEnd = text.charAt(s) == text.charAt(e);

                    if(s == e) {
                        dp[s][e] = true;
                    } else if(s + 1 == e) {
                        dp[s][e] = srtEqEnd;
                    } else {
                        dp[s][e] = srtEqEnd && dp[s + 1][e - 1];
                    }

                    if(dp[s][e] && e - s > end - srt) {
                        srt = s;
                        end = e;
                    }
                }
            }

            return text.substring(srt, end + 1);
        }
    }
}
