package baekjoon.G5_21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] param = br.readLine().split(" ");
        int n = Integer.parseInt(param[0]);
        int m = Integer.parseInt(param[1]);
        int[][] map = new int[n][n];
        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{n - 1, 0});
        clouds.add(new int[]{n - 1, 1});
        clouds.add(new int[]{n - 2, 0});
        clouds.add(new int[]{n - 2, 1});

        for(int i = 0; i < n; i++) {
            String[] mapRow = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(mapRow[j]);
            }
        }


        for (int i = 0; i < m; i++) {
            String[] moveInfo = br.readLine().split(" ");
            int d = Integer.parseInt(moveInfo[0]);
            int s = Integer.parseInt(moveInfo[1]);

            moveCloud(clouds, map, d, s);
            rain(clouds, map);
            copyWater(clouds, map);
            clouds = generateClouds(clouds, map);
        }


        int result = 0;
        for(int[] row : map){
            for (int water : row) {
                result += water;
            }
        }

        System.out.println(result);
    }

    private static void moveCloud(List<int[]> clouds, int[][] map, int d, int s) {
        int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

        for (int[] cloud : clouds) {
            int r = cloud[0];
            int c = cloud[1];

            cloud[0] = checkIdx(r + (s * dr[d - 1]), map.length);
            cloud[1] = checkIdx(c + (s * dc[d - 1]), map.length);
        }
    }

    private static int checkIdx(int calcIdx, int mapLength){
        if(calcIdx >= 0){
            return calcIdx%mapLength;
        }else if(calcIdx%mapLength == 0){
            return 0;
        }else {
            return mapLength + (calcIdx%mapLength);
        }
    }

    private static void rain(List<int[]> clouds, int[][] map) {
        for (int[] cloud : clouds) {
            map[cloud[0]][cloud[1]]++;
        }
    }

    private static void copyWater(List<int[]> clouds, int[][] map) {
        int[] dr = {-1, -1, 1, 1};
        int[] dc = {-1, 1, -1, 1};

        for (int[] cloud : clouds) {
            int r = cloud[0];
            int c = cloud[1];
            int waterBasketCount = 0;
            for(int i = 0; i < 4; i++){
                int crossRow = r + dr[i];
                int crossCol = c + dc[i];

                if (crossRow >= 0 && crossRow < map.length
                        && crossCol >= 0 && crossCol < map.length
                        && map[crossRow][crossCol] > 0) {
                    waterBasketCount++;
                }
            }

            map[r][c] += waterBasketCount;
        }
    }

    private static List<int[]> generateClouds(List<int[]> clouds, int[][] map) {

        List<int[]> newClouds = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] >= 2){
                    boolean wasCloud = false;
                    for(int[] cloud: clouds){
                        if(cloud[0] == i && cloud[1] == j) wasCloud = true;
                    }

                    if (!wasCloud) {
                        map[i][j] -= 2;
                        newClouds.add(new int[]{i, j});
                    }
                }
            }
        }

        return newClouds;
    }

}
