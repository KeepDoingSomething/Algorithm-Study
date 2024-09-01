package programmers.level2.두큐합같게만들기;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        long totalSum = sum1 + sum2;
        if (totalSum  % 2 != 0) {
            return -1;
        }

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (Integer i : queue1) q1.offer(i);
        for (Integer i : queue2) q2.offer(i);

        int maxOperations = 3 * queue1.length;
        for (int i = 0; i < maxOperations; i++) {
            if (sum1 > sum2) {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.offer(q1.poll());
            } else if (sum2 > sum1) {
                sum1 += q2.peek();
                sum2 -= q2.peek();
                q1.offer(q2.poll());
            } else {
                return answer;
            }

            answer++;
        }

        return -1;
    }
}
