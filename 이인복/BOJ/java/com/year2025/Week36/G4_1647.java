/**
 * Author    : Lee In Bok
 * Date      : 2025.02.25(Tue)
 * Runtime   : 2228 ms
 * Memory    : 360412 KB
 * Algorithm : MST
 */
package com.year2025.Week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_1647 {

    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Node>[] graph = new ArrayList[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from  = Integer.parseInt(st.nextToken());
            int to  = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        System.out.println(search(graph));
    }

    public static int search(List<Node>[] graph) {
        Queue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int total = 0;
        int max = 0;

        q.add(new Node(1, 0));

        while(!q.isEmpty()) {
            Node curNode = q.poll();

            if(visited[curNode.from]) {
                continue;
            } else {
                visited[curNode.from] = true;
            }

            total += curNode.cost;
            max = Math.max(max, curNode.cost);

            for(Node nextNode : graph[curNode.from]) {
                q.add(new Node(nextNode.from, nextNode.cost));
            }
        }

        return total - max;
    }

    static class Node implements Comparable<Node> {
        int from;
        int cost;

        public Node(int from, int cost) {
            this.from = from;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
