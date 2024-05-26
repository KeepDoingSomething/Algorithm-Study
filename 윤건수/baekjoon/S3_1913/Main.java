package baekjoon.S3_1913;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 달팽이 (Silver 3)
// https://www.acmicpc.net/problem/1913

public class Main {

    static int[][] snail;
    static int rowIdx;
    static int colIdx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        snail = new int[n][n];
        rowIdx = n/2;
        colIdx = n/2;
        int number = 1;
        snail[rowIdx][colIdx] = number++;

        int targetRow = rowIdx + 1;
        int targetCol = colIdx + 1;

        int[] directionRow = new int[]{0, 1, 0, -1};
        int[] directionCol = new int[]{1, 0, -1, 0};

        for(int i = 1; i <= n; i = i+2){
            int directionCycle =  i - 1;

            for(int j = 0; j < directionCycle * 4; j++){

                // 0: Right, 1: Down, 2: Left, 3: Up
                int direction = j/directionCycle;
                rowIdx += directionRow[direction];
                colIdx += directionCol[direction];

                if(number == target){
                    targetRow = rowIdx + 1;
                    targetCol = colIdx + 1;
                }

                snail[rowIdx][colIdx] = number++;
            }

            rowIdx += directionRow[3];
            colIdx += directionCol[2];
        }

        StringBuilder snailString = new StringBuilder();

        for(int[] snailRow : snail){
            for(int num : snailRow){
                snailString.append(num).append(" ");
            }
            snailString.deleteCharAt(snailString.length() - 1);
            snailString.append(System.lineSeparator());
        }

        System.out.print(snailString);
        System.out.print(targetRow + " " + targetCol);
    }

}
