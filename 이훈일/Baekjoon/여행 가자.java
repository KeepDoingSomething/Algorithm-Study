package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1976 {
    static int N, M, start;
    static boolean[][] graph;
    static HashSet<Integer> set, result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    graph[i][j] = true;
                }
            }
        }

        set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            int node = Integer.parseInt(st.nextToken()) - 1;
            if(i == 0) {
                start = node;
            }
            set.add(node);
        }

        result = new HashSet<>();
        visited = new boolean[N];
        bfs();

        boolean isPossible = true;
        for(int node : set) {
            if(!result.contains(node)) {
                isPossible = false;
                break;
            }
        }

        if(isPossible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        result.add(start);
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for(int i = 0; i < N; i++) {
                if(graph[node][i] && !visited[i]) {
                    queue.add(i);
                    result.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
