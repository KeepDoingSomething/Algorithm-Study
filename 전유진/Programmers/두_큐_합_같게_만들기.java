import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;
/**
 *
 *   Author    : 전유진
 *    Date      : 2024.08.31(토)
 *     Algorithm : 큐
 *
 */
public class 두_큐_합_같게_만들기 {
    public static void main(String[] args) {

        int[] queue1 = {1, 2, 1, 2};
        int[] queue2 = {1, 10, 1, 2};
        System.out.println(solution(queue1, queue2));
        ;

    }

    private static int solution(int[] queue1, int[] queue2) {
        Queue<Integer> queue_1 = new LinkedList<>();
        Queue<Integer> queue_2 = new LinkedList<>();

        long sum1 = 0, sum2 = 0; // 큐의 합을 저장할 변수
        for (int num : queue1) {
            queue_1.add(num);
            sum1 += num;
        }

        for (int num : queue2) {
            queue_2.add(num);
            sum2 += num;
        }

        int maxOperations = queue1.length * 3; // 최대 연산 수 제한

        int cnt = 0;
        while (cnt <= maxOperations) {
            if (sum1 == sum2) return cnt;

            if (sum1 > sum2) {
                if (!queue_1.isEmpty()) {
                    int value = queue_1.poll();
                    sum1 -= value;
                    sum2 += value;
                    queue_2.add(value);
                }
            } else {
                if (!queue_2.isEmpty()) {
                    int value = queue_2.poll();
                    sum2 -= value;
                    sum1 += value;
                    queue_1.add(value);
                }
            }
            cnt++;
        }

        return -1; // 두 큐의 합을 같게 만들 수 없는 경우
    }

}
