package programmers.level2.소수찾기;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    Set<Integer> numberSet;

    public int solution(String numbers) {
        int answer = 0;
        numberSet = new HashSet<>();
        combination("", numbers);
        for (Integer num : numberSet) {
            answer += isPrime(num) ? 1 : 0;
        }

        return answer;
    }

    private void combination(String comb, String others) {
        // comb는 조합된 숫자, others는 조합되지 않는 숫자
        if (!comb.isEmpty()) {
            numberSet.add(Integer.parseInt(comb));
        }

        for (int i = 0; i < others.length(); i++) {
            combination(comb + others.charAt(i),
                    others.substring(0, i) + others.substring(i + 1));
        }
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }
}
