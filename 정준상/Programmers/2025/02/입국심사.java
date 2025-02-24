import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-02-17
 * Runtime          :   70.64 ms
 * Memory           :   106 MB
 * Used algorithm   :   Binary search
 *
 * https://dev-hiro.tistory.com/144 를 참조해 풀이하였습니다.
 */
class Solution {

    /*
    - 풀이 방법 :
        "시간별 상담될 수 있는 최대 수" 를 기준으로 이진 탐색을 진행할 수 있다.

    - [현재 시간 <---> 상담수] 로 관점 바꾸기
    문제를 보면 아래와 같은 언급이 있다.
    " 더 빨리 끝나는 심사대가 있으면 **기다렸다가** 그곳으로 가서 심사를 받을 수도 있습니다. "

    예를 들어 심사관 2 명이 times=[100, 5] 이며, n = 5 이라 생각해보자.
    이 경우 [100] 이 압도적으로 시간이 많이 걸리므로, 모든 사람이 [5] 인 심사관에 찾아가야 한다.

    그러면 [100, 5] 상황에서 [100] 이 상담하는 경우는 어떤 경우일까?
    우리는 직관적으로 n >= 20 인 것을 알고 있다.
    하지만 관점을 바꿔 "현재 시간 T 일때, 상담될 수 있는 사람 수" 로 생각해보자.

    먼저 T = 50 이라 해보자. 그럼 [5] 은 10 명을 상담할 수 있으며, [100] 은 아무도 상담할 수 없다. (총 10 명)
    T = 100 이면 [5] 는 20 명, [100] 은 1 명 상담할 수 있다. (총 21 명)

    즉, 현재 시간에 따라 상담원 별 상담할 수 있는 인원은 정해져 있고, 이를 통해 최대 상담자 수를 파악할 수 있다는 것이다.

    이러한 점 때문에 "현재 시각을 줄여가며" 최대 상담자 수를 n 과 비교하는 풀이법이 가능하고, 이는 이진 탐색을 활용할 수 있다.
     */

    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};

        System.out.println(new Solution().solution(n, times));
    }

    public long solution(int n, int[] times) {
        assert times.length >= 1;

        // 가장 최악 경우는 [times.max] 에 n 명이 배정되는 경우
        long timeL = 0;
        long timeR = (long) n * Arrays.stream(times).max().getAsInt();

        while (timeL < timeR - 1) {

            long timeMid = (timeL + timeR) >>> 1;
            long peoples = availablePeoples(timeMid, times);

            // n 명 이상 배정 가능하므로 최적화 가능
            if (peoples >= n) {
                timeR = timeMid;
            }

            // n 명 배정 불가능하므로 재탐색
            else {
                timeL = timeMid;
            }
        }

        return timeR;
    }

    private long availablePeoples(long currentTime, int[] times) {
        return Arrays.stream(times)
                .mapToLong(i -> currentTime / i)
                .sum();
    }
}
