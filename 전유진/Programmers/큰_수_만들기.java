import java.util.HashSet;
import java.util.Set;

/**
 * Author    : 전유진
 * Date      : 2024.08.10(토)
 * Algorithm : 그리디
 * No
 *
 */
public class 큰_수_만들기 {

    public static void main(String[] args) {
        String number = "4177252841";
        int k = 2;
        solution(number, k);
    }

    private static String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int idx = 0;
        for (int i = 0; i < number.length() - k; i++) {
            int max = 0;
            for (int j = idx; j <= i + k; j++) {
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    idx = j + 1;
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }
}
