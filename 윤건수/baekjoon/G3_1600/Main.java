package baekjoon.G3_1600;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    static int[] dr = {1, 0, -1, 0, -2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dc = {0, 1, 0, -1, -1, 1, -2, 2, -2, 2, -1, 1};
    static int rowLength;
    static int colLength;
    static final int WALL = 1;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        String[] sizeInfo = br.readLine().split(" ");
        colLength = Integer.parseInt(sizeInfo[0]);
        rowLength = Integer.parseInt(sizeInfo[1]);
        int[][] map = new int[rowLength][colLength];


        int idx = -1;
        while (++idx < rowLength) {
            map[idx] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int result = bfs(k, map);
        System.out.println(result);
    }

    private static int bfs(int k, int[][] map) {
        int result = -1;
        Queue<Move> que = new ArrayDeque<>();
        boolean[][][] visited = new boolean[rowLength][colLength][k + 1];
        que.add(new Move(0, 0, k, 0));

        while (!que.isEmpty()) {
            Move move = que.poll();
            int curRow = move.row;
            int curCol = move.col;
            int curK = move.k;

            if(visited[curRow][curCol][curK]) continue;
            visited[curRow][curCol][curK] = true;

            if(curRow == rowLength - 1 && curCol == colLength - 1) {
                result = move.cnt;
                break;
            }

            for(int i = 0; i < dr.length; i++) {
                if(i > 3 && curK < 1) break;
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];
                int nextMoveCnt = move.cnt + 1;
                int nextK = i > 3 ? curK - 1 : curK;

                if(nextRow >= 0 && nextCol >= 0
                        && nextRow < rowLength && nextCol < colLength
                        && map[nextRow][nextCol] != WALL
                        && !visited[nextRow][nextCol][nextK]) {
                    que.add(new Move(nextRow, nextCol, nextK, nextMoveCnt));
                }
            }
        }

        return result;
    }

    private static class Move{
        int row;
        int col;
        int k;
        int cnt;

        public Move(int row, int col, int k, int cnt) {
            this.row = row;
            this.col = col;
            this.k = k;
            this.cnt = cnt;
        }
    }

}
