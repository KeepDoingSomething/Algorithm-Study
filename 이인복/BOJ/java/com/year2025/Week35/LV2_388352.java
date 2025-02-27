/**
 * Author    : Lee In Bok
 * Date      : 2025.02.18(Tue)
 * Runtime   : 133.06 ms
 * Memory    : 116 MB
 * Algorithm : Backtracking
 */
package com.year2025.Week35;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LV2_388352 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(
                        15,
                        new int[][] {{2, 3, 9, 12, 13}, {1, 4, 6, 7, 9}, {1, 2, 8, 10, 12}, {6, 7, 11, 13, 15}, {1, 4, 10, 11, 14}},
                        new int[] {2, 1, 3, 0, 1}
                )
        );
    }

    static class Solution {

        public static boolean[] visited;
        public static int answer = 0;

        public int solution(int n, int[][] q, int[] ans) {
            visited = new boolean[n + 1];
            List<Set<Integer>> sets = Arrays.stream(q)
                                            .map(e -> Arrays.stream(e)
                                                            .boxed()
                                                            .collect(Collectors.toSet())
                                            )
                                            .collect(Collectors.toList());

            backtrack(1, 0, sets, ans);

            return answer;
        }

        public void backtrack(int combIdx, int combCnt, List<Set<Integer>> sets, int[] ans) {
            if(combCnt == 5) {
                checkPossibility(sets, ans);

                return;
            }

            for(int i = combIdx; i < visited.length; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    backtrack(i + 1, combCnt + 1, sets, ans);
                    visited[i] = false;
                }
            }
        }

        public void checkPossibility(List<Set<Integer>> sets, int[] ans) {
            int totalCnt = 0;
            List<Integer> trueList = new ArrayList<>();

            // 현재 조합 (방문된 숫자 취합)
            for(int t = 1; t < visited.length; t++) {
                if(visited[t]) {
                    trueList.add(t);
                }
            }

            for(int i = 0; i < ans.length; i++) {
                int cnt = 0;
                // 주어진 q 를 HashSet 으로 만들어 놓은 자료 구조로 일치한 숫자 갯수 확인
                Set<Integer> set = sets.get(i);

                for(Integer trueIdx : trueList) {
                    if(set.contains(trueIdx)) {
                        cnt++;
                    }
                }

                if(ans[i] == cnt) {
                    totalCnt++;
                }
            }

            if(totalCnt == sets.size()) {
                answer++;
            }
        }
    }
}
