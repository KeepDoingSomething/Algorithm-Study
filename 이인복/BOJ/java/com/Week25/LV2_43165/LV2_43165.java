/**
 * Author    : Lee In Bok
 * Date      : 2024.11.18(Mon)
 * Runtime   : 6.77 ms
 * Memory    : 81.8 MB
 * Algorithm : Prime Number, Two Pointer
 */

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
            int plus = acc + numbers[idx];  // + 했을 경우
            int minus = acc - numbers[idx];  // - 했을 경우

            if(idx == numbers.length - 1) {  // 모든 배열을 순회 했을 때
                if(plus == target || minus == target) ans++;  // 타겟 넘버인 경우

                return;
            }

            backtrack(idx + 1, plus, numbers, target);
            backtrack(idx + 1, minus, numbers, target);
        }
    }
}
