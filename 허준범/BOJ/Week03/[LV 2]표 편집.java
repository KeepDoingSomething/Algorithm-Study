/**
 * Author    : Heo jun boem
 * Date      : 2024.05.27(Mon)
 * score     : 28.0 / 100.0
 * efficiency: 0 / 10
 * Algorithm : Implementation
 */

import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {

        String[] temp = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        String answer = solution(8, 2, temp);

        System.out.println(answer);

    }

    public static String solution(int n, int k, String[] cmd) {

        String[] answerAry = new String[n];
        Stack<Integer> delStack = new Stack<>();
        int select = k;

        // 초기 표 값 설정
        Arrays.fill(answerAry, "O");

        // 명령어 뽑아서 for문 돌림
        for (String c : cmd) {
            String[] tempCmd = c.split(" ");

            int count = 0;

            // C, Z 일 경우
            if (tempCmd.length == 1) {
                // C
                if (tempCmd[0].equals("C")) {
                    answerAry[select] = "X";

                    // 최근 삭제 목록에 추가
                    delStack.add(select);

                    // 삭제 후 인덱스 에러가 날 경우 위로 선택
                    if (select+1 < n) {
                        while (answerAry[select].equals("X")) {
                            select++;
                        }
                    }
                    // 이상 없을경우 밑으로 선택
                    else {
                        while (answerAry[select].equals("X")) {
                            select--;
                        }
                    }
                }

                // Z
                if (tempCmd[0].equals("Z")) {
                    int index = delStack.pop();

                    answerAry[index] = "O";
                }
            }
            // U, D 일 경우
            else {
                // U
                if (tempCmd[0].equals("U")) {
                    count = Integer.parseInt(tempCmd[1]);

                    while (count > 0) {
                        select--;
                        // O 일 경우에만 count 셈
                        if (answerAry[select].equals("O")){
                            count--;
                        }
                    }
                }

                // D
                if (tempCmd[0].equals("D")) {
                    count = Integer.parseInt(tempCmd[1]);

                    while (count > 0) {
                        select++;
                        // O 일 경우에만 count 셈
                        if (answerAry[select].equals("O")){
                            count--;
                        }
                    }
                }
            }

        }

        // 답 return
        String answer = "";
        for (String temp : answerAry) {
            answer += temp;
        }

        return answer;
    }
}
