/**
 * Author    : Lee In Bok
 * Date      : 2024.11.14(Thu)
 * Runtime   : 14.48 ms
 * Memory    : 86.8 MB
 * Algorithm : Backtracking
 */

package com.year2024.Week24.LV2_92342;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LV2_92342 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        for(int n : sol.solution(4, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3})) {
            System.out.print(n + " ");
        }
    }

    static class Solution {

        private int[] info;  // 어피치 양궁 점수 기록
        private int targetScore;  // 어피치 총점
        private Answer answer;  // 라이언이 위해 필요한 값

        public int[] solution(int N, int[] info) {
            this.info = reverseArr(info);
            this.targetScore = IntStream.range(0, info.length)
                                        .filter(idx -> this.info[idx] > 0)
                                        .sum();

            backtrack(10, 0, N, new int[11]);

            /*
                1. answer 가 초기화 되지 않은 경우 || 아파치와 라이언이 점수 차이가 0점인 경우 [-1] 반환
                2. 처음에 뒤집어 놓은 배열 다시 뒤집어서 반환
             */
            return answer == null || answer.sum == 0 ? new int[]{-1} : reverseArr(answer.ansArr);
        }

        public void backtrack(int idx, int score, int arrowCnt, int[] scoreArr) {
            // idx 가 0점 까지 도달 했는데 남은 화살이 있다면 0점에 소비(아래 종료 조건에 들어가기 위해서 강제로 맞춰줌)
            if(idx == 0){
                scoreArr[0] = arrowCnt;
                arrowCnt = 0;
            }

            // 화살을 전부 소진 && 어피치 점수보다 같거나 높은 경우
            if(arrowCnt == 0 && score >= targetScore) {
                Answer tempAns = new Answer(scoreArr, score - targetScore);

                /*
                    1. null 인 경우: 한 번도 조건 answer 가 초기화 되지 않았을 때 조건 없이 바로 대입
                    2. 조건에 따라서 할당 (가장 큰 점수 차이, 점수가 같으면 가장 낮은 점수)
                 */
                answer = Objects.isNull(answer)
                       ? tempAns
                       : answer.getOptimizedAnswer(tempAns);

                // 강제로 맞춰준 0점 화살 다시 원복
                if(idx == 0) {
                    scoreArr[0] = 0;
                }

                return;
            }

            for(int i = idx; i >= 0; i--) {
                int minArrowToGetPoint = info[i] + 1;  // 점수를 얻기 위한 최소 화살 개수
                boolean hasEnoughArrows = minArrowToGetPoint <= arrowCnt;  // 최소 화살과 남은 화살 비교

                if(hasEnoughArrows) {
                    if(info[i] > 0) targetScore -= i;
                    score += i;
                    arrowCnt -= minArrowToGetPoint;
                    scoreArr[i] = minArrowToGetPoint;
                }

                backtrack(i - 1, score, arrowCnt, scoreArr);

                if(hasEnoughArrows) {
                    if(info[i] > 0) targetScore += i;
                    score -= i;
                    arrowCnt += minArrowToGetPoint;
                    scoreArr[i] = 0;
                }
            }
        }

        /**
         * 입력이 10점 부터 입력 되기 때문에 배열[0] 이 10점 이다.
         * 문제 풀기 편하게 배열[10] 이 10점 으로 표현 으로 변경
         * @param arr 양궁 점수 배열
         * @return 역정렬 점수 배열
         */
        private int[] reverseArr(int[] arr) {
            List<Integer> list = Arrays.stream(arr)
                                       .boxed()
                                       .collect(Collectors.toList());
            Collections.reverse(list);

            return list.stream().mapToInt(Integer::valueOf).toArray();
        }
    }

    static class Answer implements Comparable<Answer> {
        int[] ansArr;
        int sum;
        int lowestScore = Integer.MAX_VALUE;

        public Answer(int[] ansArr, int sum) {
            this.ansArr = Arrays.copyOf(ansArr, ansArr.length);
            this.sum = sum;

            for(int i = 0; i < ansArr.length; i++) {
                if(ansArr[i] > 0) {
                    this.lowestScore = Math.min(this.lowestScore, i);
                }
            }
        }

        /**
         * 새로운 정답 후보와 비교해서 최적의 해를 반환
         * @param temp 새로운 후보
         * @return 최적의 후보
         */
        public Answer getOptimizedAnswer(Answer temp) {
            return this.compareTo(temp) >= 0 ? temp : this;
        }

        /**
         * 점수가 같은 경우 화살로 쏜 가장 낮은 점수를 기준 아니면
         * 가장 큰 점수 차이를 낼 수 있는 기준
         * @param ans the object to be compared.
         * @return 비교 결과
         */
        @Override
        public int compareTo(Answer ans) {
            if(this.sum == ans.sum) {
                return this.lowestScore - ans.lowestScore;
            }

            return ans.sum - this.sum;
        }
    }
}
