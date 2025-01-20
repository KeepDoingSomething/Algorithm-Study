/**
 * Author    : Lee In Bok
 * Date      : 2024.11.06(Wed)
 * Runtime   : 856 ms
 * Memory    : 152820 KB
 * Algorithm : Dijkstra
 */

package com.year2024.Week23.G4_10282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_10282 {

    public static final int MAX_COST = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());  // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken());  // 의존성 개수
            int c = Integer.parseInt(st.nextToken());  // 해킹 당한 컴퓨터 번호(시작점)
            int[] dist = new int[n + 1];  // c 에서 각 노드 까지의 거리
            List<Node>[] graph = new ArrayList[n + 1];

            // 그래프 초기화
            for(int j = 0; j < graph.length; j++) {
                graph[j] = new ArrayList<>();
            }

            Arrays.fill(dist, MAX_COST);  // 거리 배열 초기화

            for(int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());  // 목적지
                int b = Integer.parseInt(st.nextToken());  // 출발지
                int s = Integer.parseInt(st.nextToken());  // 감염 까지의 시간

                graph[b].add(new Node(a, s));  // b -> a
            }

            sb.append(dijkstra(c, graph, dist))
              .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    private static String dijkstra(int c, List<Node>[] graph, int[] dist) {
        Queue<Node> pQueue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        Set<Integer> nodeCnt = new HashSet<>();  // 총 감염 컴퓨터 수

        dist[c] = 0;  // 출발지 설정
        pQueue.add(new Node(c, 0));

        while(!pQueue.isEmpty()) {
            Node curNode = pQueue.poll();

            nodeCnt.add(curNode.dest);

            if(curNode.cost > dist[curNode.dest]) continue;

            for(Node nextNode: graph[curNode.dest]) {
                int newDist = curNode.cost + nextNode.cost;  // 현 노드 에서 다음 노드 까지 시간

                if(newDist < dist[nextNode.dest]) {
                    dist[nextNode.dest] = newDist;
                    pQueue.add(new Node(nextNode.dest, newDist));
                }
            }
        }

        // 컴퓨터 개수 - 최대 시간
        return nodeCnt.size() + " " + Arrays.stream(dist).filter(e -> e != MAX_COST).max().orElseGet(() -> 0);
    }

    static class Node {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}