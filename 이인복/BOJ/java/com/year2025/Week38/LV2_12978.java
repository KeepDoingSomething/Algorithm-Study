/**
 * Author    : Lee In Bok
 * Date      : 2025.03.09(Sun)
 * Runtime   : 4.11 ms
 * Memory    : 107 MB
 * Algorithm : Dijkstra
 */

package com.year2025.Week38;

import java.util.*;

public class LV2_12978 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(6, new int[][]{{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}} , 4)
        );
    }

    static class Solution {
        public int solution(int N, int[][] road, int K) {
            List<Node>[] graph = new ArrayList[N + 1];

            for(int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int[] info : road) {
                graph[info[0]].add(new Node(info[1], info[2]));
                graph[info[1]].add(new Node(info[0], info[2]));
            }

            return (int) dijkstra(graph, N, K);
        }

        public long dijkstra(List<Node>[] graph, int N, int K) {
            Queue<Node> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);
            int[] dist = new int[N + 1];

            Arrays.fill(dist, Integer.MAX_VALUE);

            q.add(new Node(1, 0));
            dist[1] = 0;

            while(!q.isEmpty()) {
                Node cur = q.poll();

                if(cur.cost > dist[cur.to]) {
                    continue;
                }

                for(Node next : graph[cur.to]) {
                    int newDist = dist[cur.to] + next.cost;

                    if(dist[next.to] > newDist) {
                        dist[next.to] = newDist;
                        q.add(new Node(next.to, newDist));
                    }
                }
            }

            return Arrays.stream(dist)
                         .filter(e -> e <= K)
                         .count();
        }
    }

    static class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
