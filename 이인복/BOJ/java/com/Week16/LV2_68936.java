package com.Week16;

import java.util.Arrays;

public class LV2_68936 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] ans = sol.solution(new int[][]{
                {1, 1, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        });

        Arrays.stream(ans).forEach(System.out::println);
    }

    static class Solution {

        int[] answer = {0, 0};  // 0, 1 의 합

        public int[] solution(int[][] arr) {
            dividAndSum(arr, 0, 0, arr.length);

            return answer;
        }

        public void dividAndSum(int[][] arr, int x, int y, int size) {
            boolean isMergeable = true;

            for(int i = x; i < x + size; i++) {
                for(int j = y; j < y + size; j++) {
                    if(arr[x][y] != arr[i][j]) {
                        isMergeable = false;
                    }
                }
            }

            if(isMergeable) {
                answer[arr[x][y]]++;
                return;
            }

            if(size == 1) return;

            size /= 2;

            dividAndSum(arr, x, y, size);
            dividAndSum(arr, x, (y + size), size);
            dividAndSum(arr, (x + size), y, size);
            dividAndSum(arr, (x + size), (y + size), size);

        }
    }
}
