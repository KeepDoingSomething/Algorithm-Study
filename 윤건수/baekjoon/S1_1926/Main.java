package baekjoon.S1_1926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final int EMPTY = 0;
    static final int PICTURE = 1;
    static final int CHECKED = 2;
    static int picCount;
    static int picMaxSize;
    static int pickSize;

    public static void main(String[] args) throws IOException {
        picCount = 0;
        picMaxSize = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] area = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int height = area[0];
        int width = area[1];

        int[][] paper = new int[height][width];
        for(int i = 0; i < height; i++){
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < width; j++){
                paper[i][j] = row[j];
            }
        }

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(paper[i][j] == PICTURE){
                    pickSize = 0;
                    picCount++;
                    checkPaper(paper, i, j);
                    picMaxSize = Math.max(picMaxSize, pickSize);
                }
            }
        }

        System.out.println(picCount + System.lineSeparator() + picMaxSize);
    }

    private static void checkPaper(int[][] paper, int r, int c) {
        int cur = paper[r][c];
        if(cur != PICTURE){
            return;
        }

        pickSize++;
        paper[r][c] = CHECKED;

        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nextRow = r + dr[i];
            int nextCol = c + dc[i];
            if(nextRow >= 0
                    && nextCol >= 0
                    && nextCol < paper[0].length
                    && nextRow < paper.length
                    && paper[nextRow][nextCol] == PICTURE
            ){
                checkPaper(paper, nextRow, nextCol);
            }
        }

    }

}
