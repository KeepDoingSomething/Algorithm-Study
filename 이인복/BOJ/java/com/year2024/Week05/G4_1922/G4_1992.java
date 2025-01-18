/**
 * Author    : Lee In Bok
 * Date      : 2024.06.11(Tue)
 * Runtime   : 50404 KB
 * Memory    : 532 ms
 * Algorithm : Prim
 */

package com.year2024.Week05.G4_1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G4_1992 {

    public static int N;
    public static int M;
    public static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a][b] = c;
            graph[b][a] = c;
        }

        System.out.println(prim());
    }

    public static int prim() {
        PriorityQueue<Node> pQueue = new PriorityQueue<>((e1, e2) -> e1.dist - e2.dist);
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;

        pQueue.add(new Node(1, 0));

        while(!pQueue.isEmpty()) {
            Node curNode = pQueue.poll();
            int dest = curNode.end;  // 목적지

            if(!visited[dest]) {  // 방문안한 노드인 경우
                visited[dest] = true;
                cnt += curNode.dist;

                for(int next = 1; next < graph[dest].length; next++) {
                    if(graph[dest][next] != 0 && !visited[next]) {  // 노드에서 다른 노드로 간선이 존재 && 방문 안한 노드
                        pQueue.add(new Node(next, graph[dest][next]));
                    }
                }
            }
        }

        return cnt;
    }

    static class Node {
        int end;
        int dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }
}