import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-02-25
 * Runtime          :   1340 ms
 * Memory           :   315.068 MB
 * Used algorithm   :   Minimum spanning tree (Kruskal)
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int MAX_COST;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, N + 1).toArray();

        PriorityQueue<Edge> pq = new PriorityQueue<>(M);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Edge(v1, v2, cost));
        }

        int mstCost = getMstCostViaKruskal(pq);
        System.out.println(mstCost - MAX_COST);
    }

    private static int getMstCostViaKruskal(Queue<Edge> edges) {

        int cost = 0;
        while (!edges.isEmpty()) {

            Edge edge = edges.poll();

            int p1 = getRootParent(edge.v1);
            int p2 = getRootParent(edge.v2);

            if (p1 == p2)   {
                continue;
            }

            cost += edge.cost;
            MAX_COST = Math.max(edge.cost, MAX_COST);

            int root = Math.min(p1, p2);
            int child = Math.max(p1, p2);
            parents[child] = root;
        }

        return cost;
    }

    private static int getRootParent(int v) {
        return parents[v] == v ? v : (parents[v] = getRootParent(parents[v]));
    }
}

class Edge implements Comparable<Edge> {
    int v1, v2, cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}
