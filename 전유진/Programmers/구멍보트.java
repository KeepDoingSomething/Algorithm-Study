import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/***
 *  * Author    : 전유진
 *  * Date      : 2024.08.03(토)
 *  * Algorithm : 그리디
 *  x
 */

public class 구멍보트 {
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        solution(people, limit);
    }

    private static int solution(int[] people, int limit) {
        int cnt = 0;
        Arrays.sort(people);
        int index = 0;
        for (int i = people.length - 1; i >= index; i--) {
            if (people[i] + people[index] <= limit) { // 최소+ 최대 값이  limit보다 작거나 같으면
                index++; //한사람 더 태움
                cnt++; // 1 증가
            } else { // 크다면
                cnt++; //
            }

        }

        return cnt;
    }
}
