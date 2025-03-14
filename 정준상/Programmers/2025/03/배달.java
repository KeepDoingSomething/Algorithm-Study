import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-14
 * Runtime          :   8.03 ms
 * Memory           :   93.8 MB
 * Used algorithm   :   Shortest path (Dijkstra)
 */
class Solution {

    private static int N;
    private static List<Edge>[] adjacent;

    @SuppressWarnings("unchecked")
    private static void init(int n, int[][] road) {
        N = n;
        adjacent = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjacent[i] = new ArrayList<>();
        }

        for (int[] info : road) {
            int v1 = info[0] - 1;
            int v2 = info[1] - 1;
            int cost = info[2];

            adjacent[v1].add(new Edge(v2, cost));
            adjacent[v2].add(new Edge(v1, cost));
        }
    }

    public int solution(int N, int[][] road, int K) {
        init(N, road);

        int[] minCostArr = getMinCostArr(0);

        // System.out.println(Arrays.toString(minCostArr));
        
        return (int) Arrays.stream(minCostArr)
                .filter(cost -> cost <= K)
                .count();
    }

    private int[] getMinCostArr(int from) {
        boolean[] visited = new boolean[N];
        int[] minCostArr = new int[N];
        Arrays.fill(minCostArr, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(from, 0));

        while (!pq.isEmpty()) {

            Edge edge = pq.poll();
            int to = edge.to;

            if (visited[to]) {
                continue;
            }

            visited[to] = true;
            minCostArr[to] = edge.cost;

            from = to;
            
            for (Edge adj : adjacent[from]) {
                to = adj.to;
                int cost = adj.cost + minCostArr[from];
                
                if (visited[to] || minCostArr[to] < cost) {
                    continue;
                }

                pq.add(new Edge(to, cost));
            }
        }

        return minCostArr;
    }
}

class Edge implements Comparable<Edge> {

    final int to, cost;

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
        return "Edge{" +
                "to=" + to +
                ", cost=" + cost +
                '}';
    }
}