package com.year2024.Week11.LV2_42885;

import java.util.Arrays;

public class LV2_42885 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(new int[]{70, 50, 80, 50}, 100)
        );
    }

    static class Solution {
        public int solution(int[] people, int limit) {
            int ans = 0;
            int l = 0;
            int r = people.length - 1;

            Arrays.sort(people);

            while(l <= r) {
                int sum = people[l] + people[r];

                if(sum <= limit) {
                    l++;
                }

                r--;
                ans++;
            }

            return ans;
        }
    }
}