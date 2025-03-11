/**
 * Author    : Lee In Bok
 * Date      : 2025.03.09(Sun)
 * Runtime   : 480 ms
 * Memory    : 91236 KB
 * Algorithm : BFS
 */

package com.year2025.Week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_1600 {

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static int[] horseX = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static int[] horseY = {-2, -1, 1, 2, 2, 1, -1, -2};

    public static int K;
    public static int N;
    public static int M;
    public static int[][] board;
    public static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[M][N];
        visited = new boolean[M][N][K + 1];

        for(int i = 0; i < M; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        bfs();
    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.x == M - 1 && cur.y == N - 1) {
                System.out.println(cur.cnt);
                return;
            }

            // 기본 이동 상, 하, 좌, 우
            for(int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if(isValid(nextX, nextY) && !visited[nextX][nextY][cur.jump] && board[nextX][nextY] == 0) {
                    visited[nextX][nextY][cur.jump] = true;
                    q.add(new Node(nextX, nextY, cur.jump, cur.cnt + 1));
                }
            }

            // 아직 점프가 가능한 케이스
            if(cur.jump < K) {
                // 나이트 이동 케이스 8개
                for(int i = 0; i < 8; i++) {
                    int nextX = cur.x + horseX[i];
                    int nextY = cur.y + horseY[i];

                    if(isValid(nextX, nextY) && !visited[nextX][nextY][cur.jump + 1] && board[nextX][nextY] == 0) {
                        visited[nextX][nextY][cur.jump + 1] = true;
                        q.add(new Node(nextX, nextY, cur.jump + 1, cur.cnt + 1));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    static class Node {
        int x;
        int y;
        int jump;
        int cnt;

        public Node(int x, int y, int jump, int cnt) {
            this.x = x;
            this.y = y;
            this.jump = jump;
            this.cnt = cnt;
        }
    }
}
