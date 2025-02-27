/**
 * Author    : Lee In Bok
 * Date      : 2025.02.27(Thu)
 * Runtime   : 632 ms
 * Memory    : 60396 KB
 * Algorithm : Dijkstra
 */

package com.year2025.Week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G3_1238 {

    public static int[] tempDistance;
    public static List<Node>[] graph;
    public static int N;
    public static int M;
    public static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int[] roundTripDist = new int[N + 1];
        graph = new ArrayList[N + 1];
        tempDistance = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from  = Integer.parseInt(st.nextToken());
            int to  = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        for(int i = 1; i <= N; i++) {
            if(i == X) {
                continue;
            }

            dijkstra(i);
            roundTripDist[i] += tempDistance[X];  // 시작(i) 에서 도착(X) 까지 거리를 더해 준다.
        }

        dijkstra(X);

        for(int i = 1; i <= N; i++) {
            roundTripDist[i] += tempDistance[i];  // 도착점에서 각 집으로 되돌아가는 길
        }

        System.out.println(Arrays.stream(roundTripDist)
                                 .filter(e -> e != Integer.MAX_VALUE)
                                 .max()
                                 .orElseThrow(IllegalArgumentException::new));
    }

    static void dijkstra(int srt) {
        Queue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        pq.add(new Node(srt,0));
        Arrays.fill(tempDistance, Integer.MAX_VALUE);

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();

            if(tempDistance[curNode.to] < curNode.cost) continue;

            for(Node nextNode : graph[curNode.to]) {
                int nextCost = curNode.cost + nextNode.cost;

                if(nextCost < tempDistance[nextNode.to]) {
                    tempDistance[nextNode.to] = nextCost;
                    pq.offer(new Node(nextNode.to, nextCost));
                }
            }
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
