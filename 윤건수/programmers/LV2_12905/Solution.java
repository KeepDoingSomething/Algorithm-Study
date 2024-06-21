package programmers.LV2_12905;

public class Solution{

    public int solution(int[][] board){

        int answer = 0;
        int boardWidth = board[0].length;
        int boardHeight = board.length;

        for(int i = 0; i < boardHeight; i++){
            for(int j = 0; j < boardWidth; j++){
                int value = board[i][j];
                if(value == 0){
                    board[i][j] = 0;
                } else if(i - 1 < 0 || j - 1 < 0){
                    board[i][j] = 1;
                } else {
                    int square1 = board[i-1][j];
                    int square2 = board[i][j-1];
                    int square3 = board[i-1][j-1];
                    board[i][j] = Math.min(square1, Math.min(square2, square3)) + 1;
                }

                answer = Math.max(answer, (int)Math.pow(board[i][j], 2));
            }
        }
        return answer;
    }

}