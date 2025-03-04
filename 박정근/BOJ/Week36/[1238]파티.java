/**
 * Author    : Park Jeong Geun
 * Date      : 2025.02.27(Thu)
 * Runtime   : 492ms
 * Memory    : 70968KB
 * Algorithm : Dijkstra
 */

// >> 첫 번째 풀이 ( Dijkstra )
// 다익스트라로 풀이했습니다. (Solve Time : 0h 23m)

// 각각 학생마다, (학생 마을부터 X 마을까지 거리) + (X 마을부터 학생 마을까지 거리) 를 구해 최댓값을 찾습니다.
// 정방향 다익스트라 + 역방향 다익스트라를 통해 구현했습니다.

// 어떻게든 구현을 해보긴 했는데, 코드가 좀 리팩토링이 필요한 것 같아서... 어떻게 하면 더 가독성 있게 보일지 고민하다가 제출한 문제입니다.

import java.io.*;
import java.util.*;

class Edge {
    // 간선 클래스
    int nxt, cost;
    public Edge(int nxt, int cost) {
        this.nxt = nxt;
        this.cost = cost;
    }
}

class Node implements Comparable<Node> {
    // 노드 클래스 (우선순위 큐)
    int cost, idx;
    public Node(int cost, int idx) {
        this.cost = cost;
        this.idx = idx;
    }
    @Override
    public int compareTo(Node nd) { return this.cost - nd.cost; }
}

class Party {
    int n, m, x;
    ArrayList<Edge>[] graph;
    
    public Party(int n, int m, int x, ArrayList<Edge>[] g) {
        this.n = n;
        this.m = m;
        this.x = x;

        graph = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) graph[i] = new ArrayList<Edge>();

        for (int i = 0; i < n+1; i++) {
            for (Edge e : g[i]) graph[i].add(e);
        }
    }

    // k번 마을부터 X번 마을까지의 거리를 반환하는 함수
    public int toDijkstra(int k) {
        // Dijkstra
        int[] distances = new int[n+1];
        Arrays.fill(distances, (int)1e8);

        distances[k] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, k));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.cost > distances[now.idx]) continue;

            for (Edge e : graph[now.idx]) {
                int new_cost = now.cost + e.cost;
                if (new_cost < distances[e.nxt]) {
                    pq.add(new Node(new_cost, e.nxt));
                    distances[e.nxt] = new_cost;
                }
            }
        }
        return distances[x];
    }

    public int solve() {
        // 1. X번 마을부터 전체 마을까지의 거리 구하기
        int[] back_distances = new int[n+1];
        Arrays.fill(back_distances, (int)1e8);

        back_distances[x] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, x));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.cost > back_distances[now.idx]) continue;

            for (Edge e : graph[now.idx]) {
                int new_cost = now.cost + e.cost;
                if (new_cost < back_distances[e.nxt]) {
                    pq.add(new Node(new_cost, e.nxt));
                    back_distances[e.nxt] = new_cost;
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            // 2. 각각 학생마다 (학생의 마을부터 X번 마을까지 거리) + (X번 마을부터 학생의 마을까지 거리) 를 구하고 최댓값 찾기
            res = Math.max(res, toDijkstra(i) + back_distances[i]);
        }
        return res;
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) graph[i] = new ArrayList<Edge>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, t));
        }

        Party pt = new Party(n, m, x, graph);
        System.out.println(pt.solve());
    }
}

