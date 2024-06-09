package baekjoon.silver.silver3.boj2606_바이러스;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, result;
    static ArrayList<Integer>[] network;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/baekjoon/silver/silver3/boj2606_바이러스/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        network = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            network[i] = new ArrayList<>();
        }


        StringTokenizer st;
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            network[a].add(b);
            network[b].add(a);
        }
        BFS(1);
//        DFS(1);

        System.out.println(result);
    }


    private static void BFS(int node) {
        Queue<Integer> q = new LinkedList<>();

        visited[node] = true;
        q.add(node);

        while (!q.isEmpty()) {
            int curr = q.remove();

            for (int i : network[curr]) {
                if (!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                    result++;
                }
            }
        }
    }

    private static void DFS(int node) {
        visited[node] = true;
        for (Integer i : network[node]) {
            if (!visited[i]) {
                DFS(i);
                result++;
            }
        }
    }
}
