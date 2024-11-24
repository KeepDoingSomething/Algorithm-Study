/**
 * Author    : 이상경
 * Date      : 2024.11.17
 * Runtime   : 42.60 ms
 * Memory    : 149 MB
 * Algorithm : 스택 사용한 단순구현
 */

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int currentNumber = 1;

        Deque<Integer> sub = new ArrayDeque<>();

        for (int target : order) {
            if (!sub.isEmpty() && sub.peek() == target) {
                sub.pop();
                answer++;
                continue;
            }

            while (currentNumber <= order.length && currentNumber != target) {
                sub.push(currentNumber);
                currentNumber++;
            }

            if (currentNumber <= order.length) {
                answer++;
                currentNumber++;
            } else {
                break;
            }
        }
        return answer;
    }
}