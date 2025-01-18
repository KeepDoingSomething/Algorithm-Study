package com.year2024.Week13;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Arrays;

public class LV2_250136 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(new int[][]{
                    {1, 0, 1, 0, 1, 1},
                    {1, 0, 1, 0, 0, 0},
                    {1, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0},
                    {1, 0, 0, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1}
            })
        );
    }

    static class Solution {
        public int N;
        public int M;
        public int[] ansArr;
        public int[] dx = {0, 1, 0, -1};
        public int[] dy = {1, 0, -1, 0};

        public int solution(int[][] land) {
            N = land.length;
            M = land[0].length;
            ansArr = new int[M];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(land[i][j] == 1) {
                        land[i][j] = 0;
                        bfs(land, i, j);
                    }
                }
            }

            return Arrays.stream(ansArr).max().getAsInt();
        }

        public void bfs(int[][] land, int srtX, int srtY) {
            Queue<Point> queue = new LinkedList<>();
            Set<Integer> sameArea = new HashSet<>();
            int cnt = 1;

            Point srtP = new Point(srtX, srtY);

            queue.add(srtP);
            sameArea.add(srtY);

            while(!queue.isEmpty()) {
                Point curP = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextX = curP.x + dx[i];
                    int nextY = curP.y + dy[i];

                    if(isValid(nextX, nextY) && land[nextX][nextY] == 1) {
                        Point nextP = new Point(nextX, nextY);

                        queue.add(nextP);
                        sameArea.add(nextY);
                        land[nextX][nextY] = 0;
                        cnt++;
                    }
                }
            }

            for(int y : sameArea) {
                ansArr[y] += cnt;
            }
        }

        public boolean isValid(int x, int y) {
            return 0 <= x && x < N && 0 <= y && y < M;
        }

        class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
