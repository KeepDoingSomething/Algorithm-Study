/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.2(Mon)
 * Runtime   : 712ms
 * Memory    : 66424KB
 * Algorithm : mst
 */

// >> 첫 번째 풀이 ( 최소 스패닝 트리 (크루스칼) )
// 최소 스패닝 트리로 풀이했습니다. 크루스칼 알고리즘을 사용했습니다. (Solve Time : 0h 43m)
// Edge 클래스와 MinnimumSpanningTree 클래스를 만들어, 제 기능을 잘 수행하도록 캡슐화하는 데에 집중했습니다.
// 일반적인 최소 스패닝 트리 문제와 같지만, 이으려는 두 그룹 다 발전소와 이미 이어진 그룹이라면 이을 필요가 없으므로 다음 간선을 이으면 됩니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Stack;

class Edge {
    int u, v, w;
    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class MinnimumSpanningTree {
    private int n, m;
    private int[] power_station; // 발전소 여부
    private Edge[] edges; // 간선 배열
    private int[] parent; // 부모 노드 배열
    
    public MinnimumSpanningTree(int n, int m) {
        this.n = n;
        this.m = m;
        
        edges = new Edge[m];

        power_station = new int[n+1];
        Arrays.fill(power_station, 0);

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) parent[i] = i;
    }

    public void addPowerStation(int p) { power_station[p] = 1; }
    public void addEdge(int i, int u, int v, int w) { edges[i] = new Edge(u, v, w); }
    public void sortEdges() { 
        // Kruskal : 간선 정렬
        Arrays.sort(edges, (e1, e2) -> Integer.compare(e1.w, e2.w)); 
    }
    public boolean isPower(int a) { 
        // power_station[a] == 1 쓰기가 귀찮아서..
        if (power_station[a] == 1) return true;
        return false;
    }

    public int find(int a) {
        // Union Find : 부모 노드를 스택으로 찾으며, 나머지 노드 모두 부모를 동기화하는 함수
        Stack<Integer> citys = new Stack<>();

        while (a != parent[a]) {
            citys.push(a);
            a = parent[a];
        }

        while (!citys.isEmpty()) {
            parent[citys.peek()] = a; 
            citys.pop();
        }

        return a;
    }

    public void union(int a, int b) {
        // Union Find : a와 b 를 같은 그룹으로 잇는 함수
        a = find(a);
        b = find(b);

        // 발전기가 있는 그룹에 잇는다.
        if (isPower(a)) parent[b] = a;
        else parent[a] = b;
    }

    public int solve() {
        // Kruskal : 비용이 적은 노드부터 그룹을 잇는다.
        sortEdges();

        int result = 0;
        for (int i = 0; i < m; i++) {
            int now_a = find(edges[i].u);
            int now_b = find(edges[i].v);

            if (isPower(now_a) && isPower(now_b)) continue;

            if (now_a != now_b) {
                union(now_a, now_b);
                result += edges[i].w;
            }
        }

        return result;
    }
}

class Main {
	static public void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 도시의 개수
        int m = Integer.parseInt(st.nextToken()); // 설치 가능한 케이블의 수

        int k = Integer.parseInt(st.nextToken()); // 발전소의 개수

        // 최소 스패닝 트리 Class 생성
        MinnimumSpanningTree mst = new MinnimumSpanningTree(n, m);

        st = new StringTokenizer(br.readLine());
        // 발전소 정보 등록하기
        for (int i = 0; i < k; i++) mst.addPowerStation(Integer.parseInt(st.nextToken()));

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 간선 정보 등록하기
            mst.addEdge(i, u, v, w);
        }


        // 최소 스패닝 트리를 만들며, 만약 이미 발전소랑 이어진 그룹이라면 잇지 않는다.
        System.out.println(mst.solve());
        
    }
}
