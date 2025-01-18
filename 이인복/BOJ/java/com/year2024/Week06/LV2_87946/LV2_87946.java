package com.year2024.Week06.LV2_87946;

public class LV2_87946 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(
                sol.solution(80, new int[][]{{80,20},{50,40},{30,10}})
        );
    }

    static class Solution {

        public int ans = 0;
        public int dungeonCnt;
        public boolean[] visited;

        public int solution(int k, int[][] dungeons) {
            dungeonCnt = dungeons.length;
            visited = new boolean[dungeonCnt];

            dfs(0, k, dungeons);

            return ans;
        }

        public void dfs(int cnt, int k, int[][] dungeons) {
            ans = Math.max(ans, cnt);

            for(int i = 0; i < dungeonCnt; i++) {
                int reqStamina = dungeons[i][0];
                int useStamina = dungeons[i][1];

                if(!visited[i] && k >= reqStamina) {
                    visited[i] = true;
                    dfs(cnt + 1,k - useStamina, dungeons);
                    visited[i] = false;
                }
            }
        }
    }
}
