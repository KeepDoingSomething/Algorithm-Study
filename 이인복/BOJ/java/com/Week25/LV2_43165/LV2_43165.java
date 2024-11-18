package com.Week25.LV2_43165;

public class LV2_43165 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(new int[]{4, 1, 2, 1}, 4)
        );
    }

    static class Solution {

        public int ans = 0;

        public int solution(int[] numbers, int target) {
            backtrack(0, 0, numbers, target);
            return ans;
        }

        public void backtrack(int idx, int acc, int[] numbers, int target) {
            int plus = acc + numbers[idx];
            int minus = acc - numbers[idx];

            if(idx == numbers.length - 1) {
                if(plus == target || minus == target) ans++;

                return;
            }

            backtrack(idx + 1, plus, numbers, target);
            backtrack(idx + 1, minus, numbers, target);
        }
    }
}
