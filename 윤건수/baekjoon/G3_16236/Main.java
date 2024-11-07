package baekjoon.G3_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int EMPTY = 0;
    static final int START = 9;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int babySize;
    static int mapSize;
    static int[][] map;
    static int result;
    static int eat;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapSize = Integer.parseInt(br.readLine());
        map = new int[mapSize][mapSize];
        int[] from = null;
        for(int r = 0; r < mapSize; r++){
            String[] line = br.readLine().split(" ");
            for(int c = 0; c < mapSize; c++){
                int value = Integer.parseInt(line[c]);
                if(value == START) {
                    from = new int[]{r, c, 0};
                    value = EMPTY;
                }
                map[r][c] = value;
            }
        }

        babySize = 2;
        result = 0;
        eat = 0;

        while (from != null) {
            from = findNextPosition(from);
        }

        System.out.println(result);
    }

    private static int[] findNextPosition(int[] from){
        boolean[][] visited = new boolean[mapSize][mapSize];
        visited[from[0]][from[1]] = true;

        PriorityQueue<int[]> queue = new PriorityQueue<>((p1, p2) -> {
            if(p1[2] != p2[2]){
                return  p1[2] - p2[2];
            }
            if(p1[0] != p2[0]){
                return p1[0] - p2[0];
            }
            return p1[1] - p2[1];
        });
        queue.add(from);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int row = cur[0];
            int col = cur[1];

            if(map[row][col] != EMPTY && map[row][col] < babySize){
                eat++;
                if(eat == babySize){
                    babySize++;
                    eat = 0;
                }
                result += cur[2];
                map[row][col] = EMPTY;
                cur[2] = 0;
                return cur;
            };

            for(int i = 0; i < 4; i++){
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];
                int nextTime = cur[2] + 1;
                if(isValid(nextRow, nextCol, visited)){
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol, nextTime});
                }
            }

        }

        return null;
    }

    private static boolean isValid(int r, int c, boolean[][] visited){
        return r >= 0 && c >= 0 && r < mapSize && c < mapSize && !visited[r][c] && (map[r][c] == EMPTY || map[r][c] <= babySize);
    }

}
