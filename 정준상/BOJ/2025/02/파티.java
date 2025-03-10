import java.io.*;
import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-02-26
 * Runtime          :   200 ms
 * Memory           :   23.532 MB
 * Used algorithm   :   Shortest path (Dijkstra)
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, X;
    private static int[][] costTable, reverseCostTable;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        costTable = new int[N][N];
        reverseCostTable = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            costTable[from][to] = cost;
            reverseCostTable[to][from] = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        int[] fromXToOthers = getMinCostArr(X, costTable);
        int[] fromOthersToX = getMinCostArr(X, reverseCostTable);

        int maxima = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxima = Math.max(maxima, fromXToOthers[i] + fromOthersToX[i]);
        }

        System.out.println(maxima);
    }

    private static int[] getMinCostArr(int from, int[][] costTable) {

        boolean[] visited = new boolean[N];

        int[] result = new int[N];
        Arrays.fill(result, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(from, 0));

        while (!pq.isEmpty()) {

            Edge e = pq.poll();
            int node = e.to;

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            result[node] = e.cost;

            int[] adjacentCosts = costTable[node];
            for (int adj = 0; adj < N; adj++) {

                int cost = adjacentCosts[adj] + e.cost;
                if (visited[adj] || cost == e.cost || cost > result[adj]) {
                    continue;
                }

                pq.add(new Edge(adj, cost));
            }
        }

        return result;
    }
}

class Edge implements Comparable<Edge> {

    int to, cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }

    @Override
    public String toString() {
        return "EdgeInfo{" +
                "to=" + to +
                ", cost=" + cost +
                '}';
    }
}
