import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Author    : 전유진
 * Date      : 2024.07.16(화)
 * Algorithm : 우선순위 큐
 * x
 */

public class 프로세스 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        solution(priorities, location);

    }

    private static int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : priorities) {
            pq.add(num);
        }
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) {
                    pq.poll();
                    answer++;
                    if(i == location) return answer;
                }
            }
        }

        return answer;
    }
}
