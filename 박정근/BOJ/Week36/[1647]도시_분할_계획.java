/**
 * Author    : Park Jeong Geun
 * Date      : 2025.02.26(Wed)
 * Runtime   : 1672ms
 * Memory    : 371656KB
 * Algorithm : Minimum Spanning Tree
 */

// >> 첫 번째 풀이 ( MST )
// 최소 스패닝 트리로 풀이했습니다. (Solve Time : 0h 15m)

// 유지비가 작은 간선부터 최소 스패닝 트리를 구축한 뒤, 사용한 간선 중 가장 유지비가 많이 드는 간선 하나를 제외해서 두 마을로 분리하면 됩니다.
// 스패닝 트리를 구한 뒤, 가장 비용이 큰 간선을 찾아 전체 비용에서 뺍니다.

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int a, b, c;
    public Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(Edge e) { return c - e.c; }
}

class CityDivide {
    int n, m;
    List<Edge> eList;
    int[] parent;
    public CityDivide(int n, int m, int[][] arr) {
        this.n = n;
        this.m = m;
        
        parent = new int[n+1];
        for (int i = 0; i < n+1; i++) parent[i] = i;

        eList = new ArrayList<>();
        for (int i = 0; i < m; i++) eList.add(new Edge(arr[i][0], arr[i][1], arr[i][2]));
        Collections.sort(eList);
    }

    public int solve() {
        int answer = 0;
        int max_edge = 0;
        
        // MST : Kruskal's Algorithm
        for (int i = 0; i < m; i++) {
            Edge e = eList.get(i);
            
            if (union(e.a, e.b)) {
                answer += e.c;
                max_edge = Math.max(max_edge, e.c);
            }
        }

        return answer - max_edge;
    }

    public int find(int a) {
        // 부모 노드 동기화를 위한 스택 사용 (재귀를 피하기 위함)
        Stack<Integer> s = new Stack<>();
        while (a != parent[a]) {
            s.push(a);
            a = parent[a];
        }

        while (!s.isEmpty()) {
            int now = s.pop();
            parent[now] = a;
        }

        return a;
    }

    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            int tmp = b; b = a; a = tmp;
        }

        if (a != b) {
            parent[b] = a;
            return true;
        }
        return false;
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        CityDivide cd = new CityDivide(n, m, arr);
        System.out.println(cd.solve());
    }
}
