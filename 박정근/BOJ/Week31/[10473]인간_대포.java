/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.18(Sat)
 * Runtime   : 124ms
 * Memory    : 14720KB
 * Algorithm : Geometry + Dijkstra
 */

// >> 첫 번째 풀이 ( 기하학 + 다익스트라 )
// 시작점. 끝점. 대포 사이 이동 간의 걸리는 시간을 그래프화하여 다익스트라로 풀이했습니다. (Solve Time : 1h 01m)
// 시작점에서는 무조건 달리기로 가야 한다는 점과, 대포를 사용하면 2초가 추가된다는 점을 유의해야 합니다.
// 정점 간의 걸리는 시간(간선)은 min(달리기로 이동할 때의 시간, 대포로 이동할 떄의 시간)을 이용해 단일화해줍니다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 2차원 좌표계 점 클래스
class Pos {
    double x, y;
    public Pos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // 두 점 사이의 거리
    public double toDist(Pos p) { return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2)); }
}

// 다익스트라 그래프 노드 클래스
class Node {
    int idx;
    double time;

    public Node(int idx, double time) {
        this.idx = idx;
        this.time = time;
    }
}

class HumanCannon {
    int n;
    ArrayList<Pos> vertex;
    ArrayList<Node>[] graph;

    public HumanCannon() {
        vertex = new ArrayList<Pos>();

        graph = new ArrayList[102]; // 다익스트라 그래프
        for (int i = 0; i < 102; i++) graph[i] = new ArrayList<Node>();
    }

    public void setNum(int n) { this.n = n; }
    public void addVertex(double x, double y) { vertex.add(new Pos(x, y)); }

    public void createGraph() {
        // 시작점
        for (int i = 1; i < n + 2; i++) {
            // 0번 정점 ~ i번 정점까지 거리
            double _dist = vertex.get(0).toDist(vertex.get(i));

            // 달리기
            graph[0].add(new Node(i, _dist / 5.0));
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = i + 1; j < n + 2; j++) {
                // i번 정점 ~ j번 정점까지의 거리
                double _dist = vertex.get(i).toDist(vertex.get(j));

                // 달리기 : _dist / 5.0
                // 대포 : (Math.abs(50.0 - _dist) / 5.0) + 2.0 
                //          -> 정점 방향으로 50m 날라간 뒤 남은 거리를 5m/s로 달려갑니다.
                graph[i].add(new Node(j, Math.min(_dist / 5.0, (Math.abs(50.0 - _dist) / 5.0) + 2.0)));
                graph[j].add(new Node(i, Math.min(_dist / 5.0, (Math.abs(50.0 - _dist) / 5.0) + 2.0)));
            }
        }
    }

    public double solve() {
        createGraph(); // 다익스트라 그래프 생성 (graph)

        double[] distances = new double[102];
        for (int i = 0; i < 102; i++) distances[i] = Double.MAX_VALUE;

        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Double.compare(o1.time, o2.time));
        pq.offer(new Node(0, 0));
        distances[0] = 0; // 시작 정점인 0을 0으로 초기화

        // Dijkstra
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (distances[now.idx] < now.time) continue; // 이미 더 빠른 경로가 있다면 스킵하기

            for (int i = 0; i < graph[now.idx].size(); i++) {
                Node idxnode = graph[now.idx].get(i);
                if (distances[idxnode.idx] > now.time + idxnode.time) {
                    distances[idxnode.idx] = now.time + idxnode.time;
                    pq.offer(new Node(idxnode.idx, distances[idxnode.idx]));
                }
            }
        }

        return distances[1]; // 목적지까지 걸리는 최소 시간 반환
    }
}

class Main {
	static public void main(String []args) throws IOException {
        // 인간 대포
        HumanCannon hc = new HumanCannon();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x, y;

        // 시작 위치 입력
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());

        hc.addVertex(x, y); // 시작 정점은 0번.

        // 목적지 입력
        st = new StringTokenizer(br.readLine());

        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());

        hc.addVertex(x, y); // 목적지 정점은 1번.

        // 대포 위치 입력
        int n = Integer.parseInt(br.readLine());
        hc.setNum(n);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            x = Double.parseDouble(st.nextToken());
            y = Double.parseDouble(st.nextToken());

            hc.addVertex(x, y); // 대포 정점은 2 ~ n + 1 번.
        }

        System.out.println(hc.solve());
    }
}
