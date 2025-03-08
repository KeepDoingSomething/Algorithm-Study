/**
 * Author    : Lee In Bok
 * Date      : 2025.03.02(Sun)
 * Runtime   : 22.95 ms
 * Memory    : 94.9 MB
 * Algorithm : Graph Search
 */
package com.year2025.Week37;

import java.util.LinkedList;
import java.util.Queue;

public class LV2_388353 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(
                        new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"},
                        new String[]{"A", "BB", "A"}
                )
        );
    }

    static class Solution {

        public static int N;
        public static int M;
        public static int ans;
        public static int[] dx = {0, 1, 0, -1};
        public static int[] dy = {1, 0, -1, 0};

        public int solution(String[] storage, String[] requests) {
            N = storage.length;
            M = storage[0].length();
            Node[][] board = new Node[N + 2][M + 2];
            ans = N * M;

            for(int i = 0; i < N; i++) {
                String[] container = storage[i].split("");

                for(int j = 0; j < M; j++) {
                    int x = i + 1;
                    int y = j + 1;

                    board[x][y] = new Node(x, y, container[j]);
                }
            }

            for(String request : requests) {
                String command = Character.toString(request.charAt(0));

                if(request.length() == 1) {
                    useForkLift(board, command);
                } else {
                    useCrane(board, command);
                }
            }

            return ans;
        }

        public static void useForkLift(Node[][] board, String request) {
            Queue<Node> q = new LinkedList<>();
            // 제거 목록을 임시 저장한다. (이유: 로직이 동작하는동안 컨테이너를 빼면 다른 컨테이너가 영향을 받음)
            Queue<Node> shipOutList = new LinkedList<>();
            boolean[][] visited = new boolean[N + 2][M + 2];

            q.add(new Node(0, 0));
            visited[0][0] = true;

            while(!q.isEmpty()) {
                Node curNode = q.poll();

                for(int i = 0; i < 4; i++) {
                    int nextX = curNode.x + dx[i];
                    int nextY = curNode.y + dy[i];
                    
                    if(isValid(nextX, nextY) && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        Node nextNode = new Node(nextX, nextY);
                        
                        // null(= empty)
                        if(board[nextX][nextY] == null) {
                            q.add(nextNode);
                        } else if(board[nextX][nextY].isShipOutTarget(request)) {
                            shipOutList.add(nextNode);
                        }
                    }
                }
            }

            // 제거 목록으로 저장된 컨테이너 좌표 삭제
            while(!shipOutList.isEmpty()) {
                Node target = shipOutList.poll();

                ans--;
                board[target.x][target.y] = null;
            }
        }

        // 크레인은 지형적인 영향을 안받기 때문에 컨테이너 전부 제거
        public static void useCrane(Node[][] board, String request) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    if(board[i][j] != null && request.equals(board[i][j].val)) {
                        ans--;
                        board[i][j] = null;
                    }
                }
            }
        }

        public static boolean isValid(int x, int y) {
            return 0 <= x && x < N + 2 && 0 <= y && y < M + 2;
        }
    }

    static class Node {
        int x;
        int y;
        String val;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, String val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public boolean isShipOutTarget(String val) {
            return this.val.equals(val);
        }
    }
}
