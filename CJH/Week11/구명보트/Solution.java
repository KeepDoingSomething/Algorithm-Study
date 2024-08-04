package programmers.level2.구명보트;

import java.util.Arrays;

public class Solution {
    /**
     * 1. 사람들의 몸무게를 정렬
     * 2. left 가장 가벼운 사람, right 는 가장 무거운 사람
     * 3. 가장 가벼운 사람과 가장 무거운 사람을 함께 태울 수 있으면 left 증가
     * 4. 둘다 못 태우면 무거운 사람만 태우고 right 감소
     * 5. 보트 사용한 횟수 증가
     */

    public int solution(int[] people, int limit) {
        int boats = 0;

        Arrays.sort(people);
        int left = 0;
        for (int right = people.length - 1; left <= right; right--) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            boats++;
        }

        return boats;
    }
}
