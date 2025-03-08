/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.08(Sat)
 * Runtime   : 112ms
 * Memory    : 14492KB
 * Algorithm : Graph Traversal + Euclidean
 */

// >> 첫 번째 풀이 ( 그래프 탐색 + 유클리드 호제법 )
// 그래프 탐색을 통해 가중치를 곱하면서 최소 질량 합을 구하는 문제입니다. (Solve Time : 0h 31m)

// a번 재료의 질량과 b번 재료의 질량의 비는 p : q 이기 때문에, 이를 간선으로 보고 그래프를 그렸을 때, 
// 특정 재료의 질량은 그래프를 전체 탐색하면서 간선들의 가중치를 곱한 값이라는 아이디어를 얻었습니다.
// 해당 아이디어에 대한 증명은, 모든 재료들의 질량 비를 유지시키기 위해서는 각 재료마다 어떤 값이 필수로 곱해져야 하는지를 생각하면 됩니다.
// 마지막에 전체 재료들의 질량을 최대공약수로 나눠주면 최소 질량 합을 구할 수 있습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.Collectors;

class Edge {
    int nxt, cost;
    public Edge(int nxt, int cost) {
        this.nxt = nxt;
        this.cost = cost;
    }
}

class August14 {
    int n;
    ArrayList<Edge>[] graph;

    public August14(int n, int[][] arr) {
        this.n = n;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<Edge>();

        for (int i = 0; i < n - 1; i++) {
            graph[arr[i][0]].add(new Edge(arr[i][1], arr[i][2]));
            graph[arr[i][1]].add(new Edge(arr[i][0], arr[i][3]));
        }
    }

    public int gcd(int a, int b) { return (b == 0) ? a : gcd(b, a % b); }

    public void printAllIngredient() {
        int[] answer = new int[n];
        
        // 재료마다 그래프를 전체 탐색하여, 지나가는 간선들의 가중치를 모두 곱한다.
        for (int i = 0; i < n; i++) {
            Queue<Integer> q = new LinkedList();
            q.add(i);

            boolean[] visited = new boolean[n];
            Arrays.fill(visited, false);
            visited[i] = true;

            int res = 1;
            
            while (!q.isEmpty()) {
                int now = q.poll();

                for (Edge e : graph[now]) {
                    if (visited[e.nxt]) continue; 
                    
                    res *= e.cost;
                    q.add(e.nxt);
                    visited[e.nxt] = true;
                }
            }

            answer[i] = res;
        }

        // 전체 재료들의 질량을 최대공약수로 나눠준다.
        int g = gcd(answer[0], answer[1]);
        for (int i = 2; i < n; i++) g = gcd(g, answer[i]);
        for (int i = 0; i < n; i++) answer[i] /= g;

        // 스트림은 너무 어려워서 검색의 힘을 빌렸습니다.. ㅠ
        System.out.println(Arrays.stream(answer)
                                 .mapToObj(String::valueOf)
                                 .collect(Collectors.joining(" ")));
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        if (n == 1) System.out.println(1);
        else {
            int[][] arr = new int[n-1][4];

            for (int i = 0; i < n-1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) arr[i][j] = Integer.parseInt(st.nextToken());
            }

            August14 a = new August14(n, arr);
            a.printAllIngredient();
        }
    }
}
