package com.Week03.LV2_86971;

import java.util.*;

public class Q86971 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(9, new int[][] {
                {1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}
        }));
    }

    static class Solution {

        public static int N;
        public static Map<Integer, Set<Integer>> graph;

        public static int solution(int n, int[][] wires) {
            graph = new HashMap<>();
            N = n;

            for(int[] wire : wires) {
                int x = wire[0] - 1;
                int y = wire[1] - 1;

                graph.computeIfAbsent(x, k -> new HashSet<>()).add(y);
                graph.computeIfAbsent(y, k -> new HashSet<>()).add(x);
            }

            System.out.println(graph);
            int minGap = Integer.MAX_VALUE;

            for(int i = 0; i < wires.length; i++) {
                int disX = wires[i][0] - 1;  // 연결 끊기는점 x
                int disY = wires[i][1] - 1;  // 연결 끊기는점 y
                boolean[] visited = new boolean[n];
                int gap = 0;

                // 그래프 연결 끊기
                graph.get(disX).remove(disY);
                graph.get(disY).remove(disX);

                for(int j = 0; j < n; j++) {
                    if(!visited[j]) {
                        gap = Math.abs(gap - bfs(j, visited));
                    }
                }
                minGap = Math.min(gap, minGap);

                // 그래프 끊은 연결 다시 회복
                graph.get(disX).add(disY);
                graph.get(disY).add(disX);
            }

            return minGap;
        }

        public static int bfs(int srt, boolean[] visited) {
            Queue<Integer> queue = new LinkedList();
            int connectCnt = 0;

            queue.add(srt);

            while(!queue.isEmpty()) {
                int curP = queue.poll();

                if(!visited[curP]) {
                    connectCnt++;
                    visited[curP] = true;
                    queue.addAll(graph.get(curP));
                }
            }

            return connectCnt;
        }
    }
}