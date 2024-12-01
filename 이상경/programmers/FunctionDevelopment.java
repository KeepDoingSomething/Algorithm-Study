import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        int current = 0;
        while (current < progresses.length) {
            int remain = (int) Math.ceil((100.0 - progresses[current]) / speeds[current]);

            for (int i = current; i < progresses.length; i++) {
                progresses[i] += speeds[i] * remain;
            }

            int count = 0;
            while (current < progresses.length && progresses[current] >= 100) {
                count++;
                current++;
            }

            if (count > 0) {
                answer.add(count);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
