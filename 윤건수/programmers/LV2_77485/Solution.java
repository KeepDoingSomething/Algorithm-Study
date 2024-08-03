package programmers.LV2_77485;

import java.util.*;

public class Solution {

    int[][] matrix;
    int saveNum;
    int curRowIdx;
    int curColIdx;
    int[] answer;
    int ansIdx = 0;

    public int[] solution(int rows, int columns, int[][] queries) {

        matrix = new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i ++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = num++;
            }
        }

        answer = new int[queries.length];
        int ansIdx = 0;

        for(int[] query : queries){
            this.turn(query);
        }

        return answer;
    }

    private void turn(int[] query){
        int startRowIdx = query[0] - 1;
        int startColIdx = query[1] - 1;
        int endRowIdx = query[2] - 1;
        int endColIdx = query[3] - 1;

        this.curRowIdx = startRowIdx;
        this.curColIdx = startColIdx;

        int[] dirRow = new int[]{0, 1, 0, -1};
        int[] dirCol = new int[]{1, 0, -1, 0};

        int dir = 0;

        saveNum = matrix[curRowIdx][curColIdx];
        int min = saveNum;

        while(dir < 4){

            while((dir == 0 && curColIdx < endColIdx)
                    || (dir == 1 && curRowIdx < endRowIdx)
                    || (dir == 2 && curColIdx > startColIdx)
                    || (dir == 3 && curRowIdx > startRowIdx)
            ){
                curColIdx += dirCol[dir];
                curRowIdx += dirRow[dir];
                int temp = saveNum;
                saveNum = matrix[curRowIdx][curColIdx];
                matrix[curRowIdx][curColIdx] = temp;
                min = Math.min(min, saveNum);
            }
            dir++;
        }

        answer[ansIdx++] = min;
    }


}