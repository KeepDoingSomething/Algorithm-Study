package com.Week12.LV2_42883;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Q42883 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(
                sol.solution("1924", 2)
        );
    }

    static class Solution {
        public String solution(String number, int k) {
            int[] nums = Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray();
            Stack<Integer> stack = new Stack<>();

            for(int i = 0; i < nums.length; i++) {
                while(!stack.isEmpty() && k > 0 && stack.peek() < nums[i]) {
                    stack.pop();
                    k--;
                }

                stack.push(nums[i]);
            }

            while(k > 0) {
                stack.pop();
                k--;
            }

            return stack.stream().map(String::valueOf).collect(Collectors.joining(""));
        }
    }
}
