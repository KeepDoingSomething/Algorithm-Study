/**
 * Author    : Lee In Bok
 * Date      : 2024.09.09(Mon)
 * Runtime   : 19.26ms
 * Memory    : 95.5MB
 * Algorithm : Graph Search
 */

package com.year2024.Week17;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LV2_169199 {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."})
        );
    }

    static class Solution {

        final int[] dx = {-1, 1, 0, 0};
        final int[] dy = {0, 0, -1, 1};

        final String GOAL = "G";
        final String BLOCK = "D";

        int N;
        int M;

        public int solution(String[] board) throws Exception {
            N = board.length;
            M = board[0].length();
            Queue<Point> queue = new LinkedList<>();
            boolean[][][] visited = new boolean[N][M][4];  // x, y, 방향
            String[][] graph = Arrays.stream(board)
                                     .map(str -> str.split(""))
                                     .toArray(String[][]::new);

            queue.add(getStartPoint(board));

            while(!queue.isEmpty()) {
                Point curP = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextX = curP.x + dx[i];
                    int nextY = curP.y + dy[i];
                    int nextDist = curP.dist + 1;

                    // 유효하지 못한 좌표 케이스
                    if(!isValid(board, nextX, nextY) || visited[nextX][nextY][i] || BLOCK.equals(graph[nextX][nextY])) {
                        continue;
                    }

                    // 유효한 좌표 && 방문 안함 && 지나갈 수 있는 길
                    while(isValid(board, nextX, nextY) && !BLOCK.equals(graph[nextX][nextY])) {
                        visited[nextX][nextY][i] = true;
                        nextX += dx[i];
                        nextY += dy[i];
                    }

                    // 유효하지 않은 좌표 원상 복구
                    nextX -= dx[i];
                    nextY -= dy[i];

                    // 다음 좌표가 목표 지점인 경우
                    if(graph[nextX][nextY].equals(GOAL)) {
                        return nextDist;
                    }

                    queue.add(new Point(nextX, nextY, nextDist));
                }
            }

            return -1;
        }

        private boolean isValid(String[] board, int x, int y) {
            return 0 <= x && x < N && 0 <= y && y < M;
        }

        private Point getStartPoint(String[] board) throws Exception {
            for(int row = 0; row < N; row++) {
                for(int col = 0; col < M; col++) {
                    if(board[row].charAt(col) == 'R') {
                        return new Point(row, col, 0);
                    }
                }
            }

            throw new Exception();  // Unreachable Code
        }

        class Point {
            int x;
            int y;
            int dist;

            public Point(int x, int y, int dist) {
                this.x = x;
                this.y = y;
                this.dist = dist;
            }
        }
    }
}