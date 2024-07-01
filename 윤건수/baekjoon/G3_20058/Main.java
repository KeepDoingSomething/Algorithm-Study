package baekjoon.G3_20058;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마법사 상어와 파이어스톰 (Gold 3)
// https://www.acmicpc.net/problem/20058

public class Main {

    static int[][] map;
    static int[][] rotateMap;
    static boolean[][] visited;
    static int mapSize;
    static final int[] dr = {0, 1, 0, -1};
    static final int[] dc = {1, 0, -1, 0};
    static int maxIceSize;
    static int iceSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        mapSize = (int) Math.pow(2, Integer.parseInt(st.nextToken()));

        map = new int[mapSize][mapSize];
        visited = new boolean[mapSize][mapSize];

        for (int r = 0; r < mapSize; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < mapSize; c++){
                int ice = Integer.parseInt(st.nextToken());
                map[r][c] = ice;
            }
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int level = Integer.parseInt(st.nextToken());
            rotate(level);
            melt();
        }

        int sum = 0;
        maxIceSize = 0;
        for(int r = 0; r < mapSize; r++){
            for (int c = 0; c < mapSize; c++) {
                int ice = map[r][c];
                sum += ice;

                if(ice > 0 && !visited[r][c]){
                    iceSize = 0;
                    dfs(r, c);
                }
            }
        }

        System.out.println(sum + System.lineSeparator() + maxIceSize);
    }

    private static void rotate(int level){
        rotateMap = new int[mapSize][mapSize];
        int areaSize = (int) Math.pow(2, level);

        for (int r = 0; r < mapSize; r += areaSize) {
            for (int c = 0; c < mapSize; c += areaSize) {
                for (int i = 0; i < areaSize; i++) {
                    for (int j = 0; j < areaSize; j++) {
                        rotateMap[r + j][c + areaSize - 1 - i] = map[r + i][c + j];
                    }
                }
            }
        }
    }

    private static void melt() {
        for (int r = 0; r < mapSize; r++) {
            for (int c = 0; c < mapSize; c++) {
                int iceCnt = 0;
                for (int i = 0; i < 4; i++) {
                    int checkRow = r + dr[i];
                    int checkCol = c + dc[i];
                    if(isValidIdx(checkRow, checkCol) && rotateMap[checkRow][checkCol] > 0) iceCnt++;
                }

                map[r][c] = iceCnt < 3 ? Math.max(rotateMap[r][c] - 1, 0) : rotateMap[r][c];
            }
        }
    }

    private static boolean isValidIdx(int row, int col){
        return row >= 0 && col >= 0 && row < mapSize && col < mapSize;
    }

    private static void dfs(int row, int col){
        visited[row][col] = true;
        iceSize++;
        maxIceSize = Math.max(iceSize, maxIceSize);

        for(int i = 0; i < 4; i++){
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];
            if(isValidIdx(nextRow, nextCol) && !visited[nextRow][nextCol] && map[nextRow][nextCol] > 0){
                dfs(nextRow, nextCol);
            }
        }
    }

}
