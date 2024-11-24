package baekjoon.G4_1956;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int town = Integer.parseInt(st.nextToken());
        int road = Integer.parseInt(st.nextToken());

        int[][] roadInfo = new int[town + 1][town + 1];
        for (int[] townRoad : roadInfo) {
            Arrays.fill(townRoad, INF);
        }

        String line = null;
        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            roadInfo[from][to] = distance;
            // roadInfo[to][from] = distance;
        }

        for(int k = 1; k <= town; k++){
            for (int i = 1; i <= town; i++) {
                for(int j = 1; j <= town; j++){
                    if(roadInfo[i][k] != INF && roadInfo[k][j] != INF){
                        roadInfo[i][j] = Math.min(roadInfo[i][j], roadInfo[i][k] + roadInfo[k][j]);
                    }
                }
            }
        }

        int result = INF;
        for (int i = 1; i <= town; i++) {
            result = Math.min(result, roadInfo[i][i]);
        }

        System.out.println(result == INF ? -1 : result);

    }

}
