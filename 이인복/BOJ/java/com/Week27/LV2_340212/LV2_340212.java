/**
 * Author    : Lee In Bok
 * Date      : 2024.12.08(Sun)
 * Runtime   : 22.13 ms
 * Memory    : 136 MB
 * Algorithm : Binary Search, Implementation
 */

package com.Week27.LV2_340212;


public class LV2_340212 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(new int[]{1, 4, 4, 2},new int[]{6, 3, 8, 2},59)
        );
    }

    static class Solution {
        public int solution(int[] diffs, int[] times, long limit) {
            return binarySearch(diffs, times, limit);
        }

        public int binarySearch(int[] diffs, int[] times, long limit) {
            long l = 1;
            long r = limit;
            int ans = Integer.MAX_VALUE;

            while(l <= r) {
                long m = (l + r) / 2;
                long res = calc(diffs, times, m);

                if(limit >= res) {
                    ans = Math.min(ans, (int)m);
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }

            return ans == Integer.MAX_VALUE ? 1 : ans;
        }

        public long calc(int[] diffs, int[] times, long level) {
            long totalTimes = times[0];

            for(int i = 1; i < times.length; i++) {
                if(diffs[i] > level) {
                    totalTimes += (times[i] + times[i - 1]) * (diffs[i] - level) + times[i];
                    continue;
                }

                totalTimes += times[i];
            }

            return totalTimes;
        }
    }
}