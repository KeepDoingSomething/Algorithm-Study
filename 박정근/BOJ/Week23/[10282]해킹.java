/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.08(Fri)
 * Runtime   : 704 ms
 * Memory    : 156124 KB
 * Algorithm : Dijkstra
 */

/**

// >> 첫 번째 풀이 ( 다익스트라 )
// 다익스트라 알고리즘을 통해 정점마다 최단 경로를 구했고, 감염된 컴퓨터의 개수와 가장 마지막에 감염되는 컴퓨터의 도달 시간은 배열 정렬을 사용해 구했습니다.
// 자바로 다익스트라 알고리즘을 구현하는 건 처음이라 검색해보며 풀이했습니다.. PriorityQueue나 ArrayList 의 사용법을 익힐 수 있었습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    static class Node {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<Node>()); // 1부터 n까지의 정점 그래프

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new Node(a, s)); // a가 다른 컴퓨터 b를 의존한다. : b -> a
            }
            
            int[] distances = new int[n+1];
            for (int i = 1; i <= n; i++) distances[i] = Integer.MAX_VALUE;

            PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
            pq.offer(new Node(c, 0));
            distances[c] = 0; // 시작 정점인 c를 0으로 초기화

            // Dijkstra
            while (!pq.isEmpty()) {
                Node now = pq.poll();
                if (distances[now.idx] < now.cost) continue; // 이미 더 빠른 경로가 있다면 스킵하기

                for (int i = 0; i < graph.get(now.idx).size(); i++) {
                    Node nextnode = graph.get(now.idx).get(i);
                    if (distances[nextnode.idx] > now.cost + nextnode.cost) {
                        distances[nextnode.idx] = now.cost + nextnode.cost;
                        pq.offer(new Node(nextnode.idx, distances[nextnode.idx]));
                    }
                }
            }

            Arrays.sort(distances);
            for (int i = n; i >= 0; i--) {
                if (distances[i] != Integer.MAX_VALUE) {
                    System.out.println(i + " " + distances[i]); // 감염된 컴퓨터 수, 최대로 걸린 시간을 distances 배열 정렬로 통해 처리
                    break;
                }
            }
        }
    }
}
