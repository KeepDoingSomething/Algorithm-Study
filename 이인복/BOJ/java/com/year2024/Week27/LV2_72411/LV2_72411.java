/**
 * Author    : Lee In Bok
 * Date      : 2024.12.08(Sun)
 * Runtime   : 34.29 ms
 * Memory    : 101 MB
 * Algorithm : Hash, Implementation
 */

package com.year2024.Week27.LV2_72411;

import java.util.*;
import java.util.stream.*;

public class LV2_72411 {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static class Solution {

        int[] maxLenEach = new int[11];
        private Map<String, Integer> menus = new HashMap<>();

        public String[] solution(String[] orders, int[] course) {
            for(String order: orders) {
                char[] tmpArray = order.toCharArray();

                Arrays.sort(tmpArray);  // 문자열 조합이 만들어 질 때 알파벳 순으로 정렬 되도록 기본 사전순 정

                dfs(0, "", new String(tmpArray));
            }

            return groupingByLength(Arrays.stream(course).boxed().toList());
        }

        public String[] groupingByLength(List<Integer> course) {
            return menus.keySet()
                        .stream()
                        .filter(e -> {
                            int len = e.length();

                            return course.contains(len)  // course 로 주어진 문자열 크기
                                && maxLenEach[len] > 1  // 1 명 이상이 주문
                                && maxLenEach[len] == menus.get(e);  // 가장 많이 주문된 조합
                        })
                        .sorted()
                        .toArray(String[]::new);

        }

        public void dfs(int combIdx, String comb, String order) {
            int courseLen = comb.length();

            if(courseLen > 1) {
                menus.put(comb, menus.getOrDefault(comb, 0) + 1);  // 조합을 기록 및 카운팅
                maxLenEach[courseLen] = Math.max(maxLenEach[courseLen], menus.get(comb));  // 가장 많이 주문된 조합의 길이 갱신
            }

            for(int i = combIdx; i < order.length(); i++) {
                dfs(i + 1, comb + order.charAt(i), order);
            }
        }
    }
}