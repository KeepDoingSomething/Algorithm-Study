package programmers.level2.쿼드압축후개수세기;

public class Solution {
    int zeroCnt;
    int oneCnt;

    public int[] solution(int[][] arr) {
        zeroCnt = 0;
        oneCnt = 0;

        partition(0, 0, arr.length, arr);
        return new int[]{zeroCnt, oneCnt};
    }

    private void partition(int row, int col, int size, int[][] arr) {
        if (isUniform(row, col, size, arr)) {
            if (arr[row][col] == 0) {
                zeroCnt++;
            } else oneCnt++;
            return;
        }

        int newSize = size / 2;
        partition(row, col, newSize, arr);
        partition(row + newSize, col, newSize, arr);
        partition(row, col + newSize, newSize, arr);
        partition(row + newSize, col + newSize, newSize, arr);
    }

    private boolean isUniform(int row, int col, int size, int[][] arr) {
        int color = arr[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] != color) return false;
            }
        }

        return true;
    }
}