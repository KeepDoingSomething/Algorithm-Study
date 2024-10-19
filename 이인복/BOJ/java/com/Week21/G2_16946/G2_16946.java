/**
 * Author    : Lee In Bok
 * Date      : 2024.10.19(Sat)
 * Runtime   : 1544 ms
 * Memory    : 279300 KB
 * Algorithm : Graph Search
 */

package com.Week21.G2_16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G2_16946 {

    public static int N;
    public static int M;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static boolean[][] visited;
    public static int countSeq = 1;
    public static int[][] countBoard;
    public static Map<Integer, Integer> countMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        countBoard = new int[N][M];
        countMap = new HashMap<>();
        int[][] board = new int[N][M];

        for(int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        // 영역별 이동 가능한 공간 구하는 로직
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 벽인 케이스 && 방문 하지 않은 케이스
                if(board[i][j] == 0 && !visited[i][j]) {
                    countBoard[i][j] = countSeq;
                    bfs(board, new Node(i, j));
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i][j] == 1) {
                    Set<Integer> set = new HashSet<>();  // 중복 되는 같은 영역 카운팅 제거

                    for(int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];

                        if(isValid(nextX, nextY) && board[nextX][nextY] == 0) {
                            set.add(countBoard[nextX][nextY]);
                        }
                    }

                    for(int num : set) {
                        board[i][j] += countMap.get(num);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int[] row : board) {
           for(int col : row) {
               sb.append(col % 10);
           }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    public static void bfs(int[][] board, Node srtNode) {
        Queue<Node> q = new LinkedList<>();
        int cnt = 0;

        q.add(srtNode);

        while(!q.isEmpty()) {
            Node curNode = q.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = curNode.x + dx[i];
                int nextY = curNode.y + dy[i];

                if(isValid(nextX, nextY) && !visited[nextX][nextY] && board[nextX][nextY] == 0) {
                    cnt++;
                    visited[nextX][nextY] = true;
                    q.add(new Node(nextX, nextY));
                    countBoard[nextX][nextY] = countSeq;
                }
            }
        }

        // cnt 가 0 이면 주변에 벽으로 둘러 쌓인 공간
        countMap.put(countSeq++, cnt == 0 ? 1 : cnt);
    }

    public static  boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
