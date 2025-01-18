/**
 * Author    : Lee In Bok
 * Date      : 2024.10.21(Mon)
 * Runtime   : 76.58 ms
 * Memory    : 85.5 MB
 * Algorithm : Backtracking
 */

package com.year2024.Week22.LV2_12952;

public class LV2_12952 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(8)
        );
    }

    static class Solution {
        boolean[] visited1;
        boolean[] visited2;
        boolean[] visited3;
        public int N;
        public int ans = 0;

        public int solution(int n) {
            N = n;
            visited1 = new boolean[N];
            visited2 = new boolean[N * 2 - 1];
            visited3 = new boolean[N * 2 - 1];
            backtrack(0);

            return ans;
        }

        public void backtrack(int row) {
            if(row == N) {
                ans++;
                return;
            }

            for(int col = 0; col < N; col++) {
                int point1 = row + col;
                int point2 = row - col + N - 1;

                if(!visited1[col] && !visited2[point1] && !visited3[point2]){
                    visited1[col] = visited2[point1] = visited3[point2] = true;
                    backtrack(++row);
                    row--;
                    visited1[col] = visited2[point1] = visited3[point2] = false;
                }
            }
        }
    }
}