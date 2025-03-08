/**
 * Author    : Lee In Bok
 * Date      : 2025.03.08(Sat)
 * Runtime   : 120 ms
 * Memory    : 15820 KB
 * Algorithm : Graph, Math
 */

package com.year2025.Week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G2_1033 {

    public static List<Node>[] graph;
    public static boolean[] visited;
    public static long[] ratio;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long lcm = 1;
        graph = new ArrayList[N];
        visited = new boolean[N];
        ratio = new long[N];

        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            // 최소 공배수 구하기 (각 노드간의 비율을 맞추기 위해서 필요)
            lcm *= p * q / gcd(p, q);
            graph[a].add(new Node(b, p, q));
            graph[b].add(new Node(a, q, p));
        }

        // 찾고자 하는 N 의 재료를 시작점으로 최소 공배수를 갖고 그래프 탐색을 통해서 노드간 비율에 따라서 조율
        long ggcd = ratio[N - 1] = lcm;
        dfs(N - 1);

        // 조율된 값을 최소 값으로 만들기 위해서 최대 공약수를 구한다.
        for(int i = 0; i < ratio.length; i++) {
            ggcd = gcd(ggcd, ratio[i]);
        }

        // 최대 공약수로 값을 나누어 출력
        for (int i = 0; i < N; i++) {
            System.out.print(ratio[i] / ggcd + " ");
        }
    }

    public static void dfs(int node) {
        visited[node] = true;

        for(Node next : graph[node]) {
            if(!visited[next.b]) {
                // 노드 사이의 정해진 비율에 따라서 값 조율
                ratio[next.b] = ratio[node] * next.q / next.p;
                dfs(next.b);
            }
        }
    }

    public static long gcd(long n1, long n2) {
        return n2 == 0 ? n1 : gcd(n2, n1 % n2);
    }

    static class Node {
       int b;
       int p;
       int q;

        public Node(int b, int p, int q) {
            this.b = b;
            this.p = p;
            this.q = q;
        }
    }
}