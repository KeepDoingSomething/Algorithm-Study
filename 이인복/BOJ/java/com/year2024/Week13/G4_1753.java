/**
 * Author    : Lee In Bok
 * Date      : 2024.08.16(Fri)
 * Runtime   : 114984 KB
 * Memory    : 724 ms
 * Algorithm : Dijkstra
 */

package com.year2024.Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_1753 {

    public static int V;
    public static int E;
    public static int K;
    public static LinkedList<Node>[] graph;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        graph = new LinkedList[V + 1];
        dist = new int[V + 1];

        for(int i = 0; i <= V; i++) {
            graph[i] = new LinkedList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(
                    new Node(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                    )
            );
        }

        dijkstra();

        for(int i = 1; i <= V; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i])
              .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    public static void dijkstra() {
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        queue.add(new Node(K, 0));
        dist[K] = 0;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            if(dist[cur.end] < cur.cost) {
                continue;
            }

            for(Node nextNode : graph[cur.end]) {
                int newCost = dist[cur.end] + nextNode.cost;

                if(dist[nextNode.end] > newCost) {
                    dist[nextNode.end] = newCost;
                    queue.add(new Node(nextNode.end, dist[nextNode.end]));
                }
            }
        }
    }

    static class Node {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}