package programmers.LV2_68936;

public class Solution {
    int[] answer = new int[2];
    public int[] solution(int[][] arr) {
        compress(arr, 0, arr.length-1, 0, arr[0].length-1);
        return answer;
    }

    private void compress(int[][] arr, int rowStart,int rowEnd, int colStart, int colEnd){
        if(isZero(arr, rowStart, rowEnd, colStart, colEnd)){
            answer[0]++;
        }else if(isOne(arr, rowStart, rowEnd, colStart, colEnd)){
            answer[1]++;
        }else{
            int rowMid = (rowStart + rowEnd)/2;
            int colMid = (colStart + colEnd)/2;
            compress(arr, rowStart, rowMid, colStart, colMid);
            compress(arr, rowMid + 1, rowEnd, colStart, colMid);
            compress(arr, rowStart, rowMid, colMid + 1, colEnd);
            compress(arr, rowMid + 1, rowEnd, colMid + 1, colEnd);
        }
    }

    private boolean isZero(int[][] arr, int rowStart,int rowEnd, int colStart, int colEnd){
        boolean result = true;
        loop: for(int i=rowStart; i <= rowEnd; i++){
            for(int j=colStart; j <= colEnd; j++){
                if(arr[i][j] != 0){
                    result = false;
                    break loop;
                }
            }
        }
        return result;
    }

    private boolean isOne(int[][] arr, int rowStart,int rowEnd, int colStart, int colEnd){
        boolean result = true;
        loop: for(int i=rowStart; i <= rowEnd; i++){
            for(int j=colStart; j <= colEnd; j++){
                if(arr[i][j] != 1){
                    result = false;
                    break loop;
                }
            }
        }
        return result;
    }
}