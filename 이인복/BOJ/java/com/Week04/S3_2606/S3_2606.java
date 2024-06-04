package com.Week04.S3_2606;

import com.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class S3_2606 implements Solution {

    @Override
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int nodes = Integer.parseInt(br.readLine());
        int edges = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new ArrayList[nodes + 1];  // 0번 요소 사용하지 않음

        for(int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        int cnt = -1;

        for(boolean infected : bfs(graph, nodes)) {
            if(infected) cnt++;
        }

        System.out.println(cnt == -1 ? 0 : cnt);
    }

    public boolean[] bfs(List<Integer>[] graph, int nodes) {
        boolean[] visited = new boolean[nodes + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);

        while(!queue.isEmpty()) {
            int curNode = queue.poll();

            for(int nextNode : graph[curNode]) {
                if(!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(nextNode);
                }
            }
        }

        return visited;
    }
}
