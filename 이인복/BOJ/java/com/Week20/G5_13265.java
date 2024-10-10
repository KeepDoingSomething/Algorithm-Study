/**
 * Author    : Lee In Bok
 * Date      : 2024.10.06(Sun)
 * Runtime   : 584 ms
 * Memory    : 141372 KB
 * Algorithm : Graph Search
 */

package com.Week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G5_13265 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeCnt = Integer.parseInt(st.nextToken());
            int edgeCnt = Integer.parseInt(st.nextToken());
            Node[] graph = new Node[nodeCnt + 1];

            // 노드 인스턴스 생성
            for(int j = 1; j <= nodeCnt; j++) {
                graph[j] = new Node();
            }

            // 간선 입력
            for(int j = 0; j < edgeCnt; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].adj.add(y);
                graph[y].adj.add(x);
            }

            run(nodeCnt, graph);
        }
    }

    public static void run(int nodeCnt, Node[] graph) {
        boolean[] visited = new boolean[nodeCnt + 1];

        // 방문 안한 노드 부터 탐색
        for(int j = 1; j < visited.length; j++) {
            if(!visited[j] && !bfs(j, graph, visited)) {
                System.out.println("impossible");
                return;
            }
        }

        System.out.println("possible");
    }

    public static boolean bfs(int srtNode, Node[] graph, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();

        visited[srtNode] = true;
        q.add(srtNode);
        graph[srtNode].color = Color.RED;

        while(!q.isEmpty()) {
            int curNode = q.poll();

            for(int nextNode : graph[curNode].adj) {
                Color nextNodeColor = graph[nextNode].color;

                if(!(nextNodeColor == Color.NONE) && nextNodeColor == graph[curNode].color) {
                    return false;
                }

                Color oppositeColor = graph[curNode].color.getOppositeColor();

                if(!visited[nextNode]) {
                    visited[nextNode] = true;
                    q.add(nextNode);
                    graph[nextNode].color = oppositeColor;
                }
            }
        }

        return true;
    }

    enum Color {
        RED, GREEN, NONE;

        public Color getOppositeColor() {
            return this == RED ? GREEN : RED;
        }
    }

    static class Node {
        List<Integer> adj = new ArrayList<>();
        Color color = Color.NONE;
    }
}