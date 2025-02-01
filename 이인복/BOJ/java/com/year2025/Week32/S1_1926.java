/**
 * Author    : Lee In Bok
 * Date      : 2025.01.25(Sat)
 * Runtime   : 440 ms
 * Memory    : 51356 KB
 * Algorithm : Breadth First Search
 */

package com.year2025.Week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_1926 {

    public static int n;
    public static int m;
    public static int maxSize = Integer.MIN_VALUE;
    public static int picCnt = 0;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static int[][] board;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        for(int row = 0 ; row < n ; row++) {
            for(int col = 0 ; col < m ; col++) {
                // 방문하지 않았고, 그림의 일부분인 경우
                if(!visited[row][col] && board[row][col] == 1) {
                    bfs(row, col);
                    picCnt++;
                }
            }
        }

        System.out.println(picCnt);
        System.out.println(maxSize);
    }

    public static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        int size = 0;

        q.add(new Point(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Point curP = q.poll();
            size++;

            for(int i = 0; i < 4; i++) {
                int nextX = curP.x + dx[i];
                int nextY = curP.y + dy[i];

                // 유효 좌표 && 방문 x && 그림의 일부분
                if(isValid(nextX, nextY) && !visited[nextX][nextY] && board[nextX][nextY] == 1) {
                    q.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }

        maxSize = Math.max(maxSize, size);
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}