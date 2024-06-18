package baekjoon.S2_1012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 유기농 배추 (Silver 2)
// https://www.acmicpc.net/problem/1012

public class Main {

    static int VISITED_AREA = 2;
    static int CABBAGE_AREA = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int cabbageNum = Integer.parseInt(st.nextToken());
            int[][] area = new int[width][height];

            for(int i = 0; i < cabbageNum; i++){
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                area[row][col] = 1;
            }

            int wormCnt = checkWormCount(area);
            bw.write(String.valueOf(wormCnt));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static int checkWormCount(int[][] area){

        int wormCnt = 0;

        for(int i = 0; i < area.length; i++){
            for(int j = 0; j < area[0].length; j++){
                if(area[i][j] == CABBAGE_AREA){
                    wormCnt++;
                    dfs(i, j, area);
                }
            }
        }

        return wormCnt;
    }

    private static void dfs(int row, int col, int[][] area){
        area[row][col] = VISITED_AREA;
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        int maxRow = area.length - 1;
        int maxCol = area[0].length - 1;

        for(int i = 0; i < 4; i++){
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];
            boolean isValidIdx = nextRow >= 0 && nextCol >= 0 && nextRow <= maxRow && nextCol <= maxCol;
            if(isValidIdx && area[nextRow][nextCol] == CABBAGE_AREA){
                dfs(nextRow, nextCol, area);
            }
        }
    }
}
