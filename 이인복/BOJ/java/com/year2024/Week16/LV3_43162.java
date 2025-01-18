package com.year2024.Week16;

public class LV3_43162 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(3, new int[][]{
                    {1, 1, 0},
                    {1, 1, 0},
                    {0, 0, 1}
            })
        );
    }

    static class Solution {
        public int solution(int n, int[][] computers) {
            boolean[] visited = new boolean[computers.length];
            int ans = 0;

            for(int i = 0; i < computers.length; i++) {
                if(!visited[i]) {
                    ans++;
                    dfs(i, visited, computers);
                }
            }

            return ans;
        }

        public void dfs(int node, boolean[] visited, int[][] computers) {
            for(int i = 0; i < computers.length; i++) {
                if(!visited[i] && computers[node][i] == 1) {
                    visited[node] = true;
                    dfs(i, visited, computers);
                }
            }
        }
    }
}
