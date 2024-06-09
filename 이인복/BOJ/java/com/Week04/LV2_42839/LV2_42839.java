package com.Week04.LV2_42839;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LV2_42839  {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("011"));
    }

    static Set<Integer> combs = new HashSet<>();
    static boolean[] visited;
    static String[] nums;

    static class Solution {
        public int solution(String numbers) {
            nums = Arrays.stream(numbers.split(""))
                         .toArray(String[]::new);
            visited = new boolean[nums.length];
            int ans = 0;

            dfs("0");

            for(int num : combs) {
                if(isPrime(num)) {
                    ans++;
                }
            }

            return ans;
        }

        public void dfs(String comb) {
            for(int i = 0; i < nums.length; i++) {
                if(!visited[i]) {
                    String newComb = comb + nums[i];
                    visited[i] = true;
                    combs.add(Integer.parseInt(newComb));
                    dfs(newComb);
                    visited[i] = false;
                }
            }
        }

        public boolean isPrime(int num) {
            if(num == 0 || num == 1) return false;

            int sqrt = (int) Math.sqrt(num) + 1;

            for(int i = 2; i < sqrt; i++) {
                if(num % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
