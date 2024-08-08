import java.util.HashSet;
import java.util.Set;

/**
 * Author    : 전유진
 * Date      : 2024.08.08(목)
 * Algorithm : 구현
 *
 */
public class 연속_부분_수열_합의_개수 {
    public static void main(String[] args) {
        int[] elements = {7, 9, 1, 1, 4};
        System.out.println(solution(elements));
    }

    private static int solution(int[] elements) {
        Set<Integer> res = new HashSet<>();
        for (int i = 1; i <= elements.length; i++) { //  길이만큼
            Set<Integer> separate = new HashSet<>();
            for (int j = 0; j < elements.length; j++) { // 1
                int sum = 0;
                int count = 0;
                for (int k = j; count < i; k++) {
                    sum += elements[k % elements.length];
                    count++;
                }
                separate.add(sum);
            }

            res.addAll(separate);
        }

        return res.size();
    }
}
