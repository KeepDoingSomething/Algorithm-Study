/**
 * Author    : Lee In Bok
 * Date      : 2024.11.14(Thu)
 * Runtime   : 14.48 ms
 * Memory    : 86.8 MB
 * Algorithm : Backtracking
 */

package com.Week24.LV2_92342;

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

        private int[] info;
        private int targetScore;
        private Answer answer;

        public int[] solution(int N, int[] info) {
            this.info = reverseArr(info);
            this.targetScore = IntStream.range(0, info.length)
                                        .filter(idx -> this.info[idx] > 0)
                                        .sum();

            backtrack(10, 0, N, new int[11]);

            return answer == null || answer.sum == 0 ? new int[]{-1} : reverseArr(answer.ansArr);
        }

        public void backtrack(int idx, int score, int arrowCnt, int[] scoreArr) {
            if(idx == 0){
                scoreArr[0] = arrowCnt;
                arrowCnt = 0;
            }

            if(arrowCnt == 0 && score >= targetScore) {
                Answer tempAns = new Answer(scoreArr, score - targetScore);

                answer = Objects.isNull(answer)
                       ? tempAns
                       : tempAns.compareTo(answer) < 0 ? tempAns : answer;

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

        @Override
        public int compareTo(Answer ans) {
            if(this.sum == ans.sum) {
                return this.lowestScore - ans.lowestScore;
            }

            return ans.sum - this.sum;
        }
    }
}
