/**
 * Author    : 이상경
 * Date      : 2024.11.10
 * Runtime   : 0.50 ms
 * Memory    : 80.4 MB
 * Algorithm : 투포인터
 */

public class ExpressionOfNumber {
    public int solution(int n) {
        int answer = 0;
        int sum = 0;
        int left = 1;
        int right = 1;

        while (true) {
            if (sum >= n) {
                sum -= left++;
            } else if (right > n) {
                break;
            } else {
                sum += right++;
            }

            if (sum == n) {
                answer++;
            }
        }
        return answer;
    }
}
