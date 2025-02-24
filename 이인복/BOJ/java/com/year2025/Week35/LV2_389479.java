/**
 * Author    : Lee In Bok
 * Date      : 2025.02.17(Mon)
 * Runtime   : 5.03 ms
 * Memory    : 88.9 MB
 * Algorithm : Implementation
 */
package com.year2025.Week35;

import java.util.LinkedList;

public class LV2_389479 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(
                new int[] {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5},3,5
        ));
    }

    static class Solution {
        public int solution(int[] players, int m, int k) {
            int ans = 0;
            LinkedList<Integer> servers = new LinkedList<>();

            for(int player : players) {
                int requiredServerCnt = player / m;

                if(requiredServerCnt >= servers.size()) {
                    int additionalServer = Math.abs(servers.size() - requiredServerCnt);  // 이미 증설한 서버 수 - 추가로 필요한 서버 수

                    for(int i = 0; i < additionalServer; i++) {
                        servers.add(k);
                        ans++;
                    }
                }

                // 시간 감소
                servers.replaceAll(e -> e - 1);

                // 서버 유지 시간 끝난 서버 제거
                while(!servers.isEmpty() && servers.peek() == 0) {
                    servers.poll();
                }
            }

            return ans;
        }
    }
}
