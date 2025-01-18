package com.year2024.Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class G4_9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine()) + 2;
            LinkedList<Integer>[] graph = new LinkedList[n];
            List<Node> points = new ArrayList<>();

            for(int j = 0; j < n; j++) {
                graph[j] = new LinkedList<>();
            }

            for(int j = 0; j < n; j++) {
                int[] point = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                points.add(new Node(point[0], point[1]));
            }

            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(getDistance(points.get(j), points.get(k)) <= 1000) {
                        graph[j].add(k);
                        graph[k].add(j);
                    }
                }
            }

            sb.append(bfs(graph)).append("\n");
        }

        System.out.println(sb);
    }

    public static String bfs(List<Integer>[] graph) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];

        queue.add(0);
        visited[0] = true;

        while(!queue.isEmpty()) {
            int curNode = queue.poll();

            if(curNode == graph.length - 1) {
                return "happy";
            }

            for(int nextNode : graph[curNode]) {
                if(!visited[nextNode]) {
                    queue.add(nextNode);
                    visited[nextNode] = true;
                }
            }
        }

        return "sad";
    }

    public static int getDistance(Node n1, Node n2) {
        return Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}