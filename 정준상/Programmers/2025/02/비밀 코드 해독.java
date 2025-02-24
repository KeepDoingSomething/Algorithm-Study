import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-02-17
 * Runtime          :   179.28 ms
 * Memory           :   149 MB
 * Used algorithm   :   Brute force
 */
class Solution {

    private static final int PW_LEN = 5;

    private Info[] initInfos(int[][] q, int[] ans)  {
        Info[] infos = new Info[q.length];

        for (int i = 0; i < q.length; i++) {
            infos[i] = new Info(q[i], ans[i]);
        }

        return infos;
    }

    // 모든 가능한 조합을 생성하는 메서드
    private List<Set<Integer>> initEveryCasesWithDFS(
            int n, int index, int prevNum, List<Set<Integer>> dst, int[] tmp
    ) {

        // 넣을 수 있는 수 모두 넣음
        if (index >= tmp.length) {

            Set<Integer> newSet = new HashSet<>();
            for (int val : tmp) {
                newSet.add(val);
            }
            dst.add(newSet);

            return dst;
        }

        // n 초과의 수를 넣으려 시도하므로 prune
        else if (prevNum >= n)  {
            return dst;
        }

        int restore = tmp[index];

        // tmp 도 문제 입력처럼 오름차순
        for (int trial = prevNum + 1; trial <= n; trial++) {
            tmp[index] = trial;
            initEveryCasesWithDFS(
                    n, index + 1, trial, dst, tmp
            );
            tmp[index] = restore;
        }

        return dst;
    }

    public int solution(int n, int[][] q, int[] ans) {

        // 모든 경우
        List<Set<Integer>> everyPossibleCases = initEveryCasesWithDFS(
                n, 0, 0, new ArrayList<>(), new int[PW_LEN]
        );
        Info[] infos = initInfos(q, ans);

        int answer = 0;

        LOOP:
        for (Set<Integer> single : everyPossibleCases) {

            // 주어진 정보와 일치 X 면 스킵
            for (Info info : infos) {
                if (!info.doesMatch(single))    {
                    continue LOOP;
                }
            }

            answer++;
        }

        return answer;
    }
}

class Info  {
    private final int[] numbers;
    private final int numOfValid;

    public Info(int[] numbers, int numOfValid) {
        this.numbers = numbers;
        this.numOfValid = numOfValid;
    }

    public boolean doesMatch(Set<Integer> numbers)  {
        int cnt = 0;
        for (int number : this.numbers) {
            if (numbers.contains(number))   {
                cnt++;
            }
        }
        return cnt == this.numOfValid;
    }
}

/*
문제의 최대 값 : n = 30, m = 10

비밀번호 개수는 5 개로 고정이므로, brute force 방식으로 해결하면 최대 ( 30C5 * 10 < 2백만 ) 연산 필요.
1 억번 미만이니 brute force 도 가능...?
 */