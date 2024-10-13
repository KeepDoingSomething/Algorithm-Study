/**
 * Author    : Kang San Ah
 * Date      : 2024.10.06(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 구현
 */

public class 혼자서하는틱택토 {

    private int countChar(String str, char ch) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }

    private boolean winCheck(String[] board, char ch) {

        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == ch && board[i].charAt(1) == ch && board[i].charAt(2) == ch) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == ch && board[1].charAt(i) == ch && board[2].charAt(i) == ch) {
                return true;
            }
        }
        if (board[0].charAt(0) == ch && board[1].charAt(1) == ch && board[2].charAt(2) == ch) {
            return true;
        }
        if (board[0].charAt(2) == ch && board[1].charAt(1) == ch && board[2].charAt(0) == ch) {
            return true;
        }
        return false;
    }

    public int solution(String[] board) {
        int o = 0; int x = 0;
        //O와 X의 개수를 센다.
        for (int i = 0; i < 3; i++) {
            o += countChar(board[i], 'O');
            x += countChar(board[i], 'X');
        }

        if (x > o || o > x+1) {
            return 0;
        }

        //O가 완성 되었을 때 X가 O의 개수와 같으면 규칙 위반
        if (winCheck(board, 'O')) {
            if (o == x) {
                return 0;
            }
        }
        //X가 완성 되었을 때 O가 X보다 1개 많으면 규칙 위반
        if (winCheck(board, 'X')) {
            if (o == x + 1) {
                return 0;
            }
        }
        return 1;
    }

}
