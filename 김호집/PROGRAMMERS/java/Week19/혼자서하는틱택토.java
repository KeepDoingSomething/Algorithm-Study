/**
 * Author    : HojipKim
 * Date      : 2024.10.19(Sat)
 * Runtime   : 0.06 ms
 * Memory    : 76.7 MB
 * Algorithm : 구현
 */

class Solution {
    public int solution(String[] board) {
        int answer = 1;

        int xNum = 0;
        int oNum = 0;

        for(String content : board){
            for (int i = 0; i < content.length(); i++) {
                if (content.charAt(i) == 'X') {
                    xNum++;
                }else if(content.charAt(i) == 'O') {
                    oNum++;
                }
            }
        }

        // case 1 : O로 먼저 시작하지않는다. (즉, X갯수가 O의갯수보다 많다)
        if(xNum > oNum){
            return 0;
        }

        // case 2 : O 갯수와 X갯수가 2개 차이가난다 (X가 1개라도 더 큰경우는 case 1에서 만족하므로 제외, O가 2 개 더 큰 경우를 찾으면돰 )
        if(oNum >= xNum+2){
            return 0;
        }

        boolean isTickTackToX = isTickTacToHorizontal('X', board) || isTickTacToVertical('X', board) || isTickTacToDiagonal('X', board);
        boolean isTickTackToO = isTickTacToHorizontal('O', board) || isTickTacToVertical('O', board) || isTickTacToDiagonal('O', board);

        // case 3 : 게임이 종료되었음에도 더 이어나간다.
        // - 1. O가 일직선이 되었는데도 불구, X의 갯수가 O와 같다.
        if(isTickTackToO && xNum == oNum){
            return 0;
        }
        // - 2. X가 일직선이 되었는데도 불구, O의 갯수가 X의 갯수보다 1개 많다
        if(isTickTackToX && oNum >= xNum+1){
            return 0;
        }
        // - 3. O와 X 둘다 일직선이다
        if(isTickTackToO && isTickTackToX){
            return 0;
        }


        return answer;
    }

    public boolean isTickTacToHorizontal(char character, String[] array){
        boolean isTickTacTo = false;
        int sameNum;
        for (int i = 0; i < array.length; i++) {
            sameNum = 0;
            for (int j = 0; j < array[i].length(); j++) {
                if (array[i].charAt(j) == character) {
                    sameNum++;
                }
            }
            if(sameNum == array[i].length()){

                return true;
            }
        }
        return isTickTacTo;
    }

    public boolean isTickTacToVertical(char character, String[] array){
        boolean isTickTacTo = false;
        int sameNum;
        for (int i = 0; i < array.length; i++) {
            sameNum = 0;
            for (int j = 0; j < array[i].length(); j++) {
                if(array[j].charAt(i) == character){
                    sameNum++;
                }
            }
            if(sameNum == array[i].length()){
                return true;
            }
        }
        return isTickTacTo;
    }

    public boolean isTickTacToDiagonal(char character, String[] array){
        boolean isTickTacTo = false;
        int sameNum = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i].charAt(i)==character){
                sameNum++;
            }
        }
        if(sameNum == array.length){

            return true;
        }
        sameNum = 0;

        for (int i = 0; i < array.length; i++) {
            int idx = array.length-1-i;
            if(array[i].charAt(idx)==character){
                sameNum++;
            }
        }
        if(sameNum==array.length){
            return true;
        }
        return isTickTacTo;
    }
}