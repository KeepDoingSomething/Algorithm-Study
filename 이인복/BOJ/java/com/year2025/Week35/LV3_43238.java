/**
 * Author    : Lee In Bok
 * Date      : 2025.02.19(Wed)
 * Runtime   : 91.97 ms
 * Memory    : 98.7 MB
 * Algorithm : Binary Search
 */
package com.year2025.Week35;

import java.util.Arrays;

public class LV3_43238 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(10, new int[]{6, 8, 10})
        );
    }

    static class Solution {
        public long solution(int n, int[] times) {
            long ans = 0;
            long l = 1;
            // 나올 수 있는 최대 시간 계산
            long r = (long)n * Arrays.stream(times).max().getAsInt();

            while(l <= r) {
                long mid = (l + r) / 2;
                // 각 심사관이 현재 시간(mid) 안에 처리할 수 있는 사람의 수
                long people = Arrays.stream(times)
                                    .mapToLong(e -> mid / e)
                                    .sum();

                if(people >= n) {
                    r = mid - 1;
                    ans = mid;
                } else {
                    l = mid + 1;
                }
            }

            return ans;
        }
    }
}
