import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * Author    : Jeon Yu Jin
 * Date      : 2024.08.16(FRI)
 * Runtime   : 316656 KB
 * Memory    : 1604 ms
 * Algorithm : dijkstra
 * X
 */

public class 최단경로 {
    static class Node{
        int next;
        int cost;

        public Node(int v, int w) {
            this.next = v;
            this.cost = w;
        }
    }

    static boolean[] visit; //방문 여부
    static int[] dist; //거리
    static ArrayList<Node>[] graph; //

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int start = sc.nextInt();
        sc.nextLine();
        graph = new ArrayList[v + 1];
        dist = new int[v + 1];
        visit = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE; // 최대값으로 전환
        }

        for (int i = 0; i < e; i++) {
            String[] split = sc.nextLine().split(" ");
            int u = Integer.parseInt(split[0]);
            int next = Integer.parseInt(split[1]);
            int w= Integer.parseInt(split[2]);

            graph[u].add(new Node(next, w));
        }

        dijstra(start, dist, graph);


        for (int i = 1; i < dist.length; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }


    }

    private static void dijstra(int start, int[] dist, ArrayList<Node>[] graph) {
        //오름 차순
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int next = node.next;
            int cost = node.cost;


            if (dist[next] < cost && visit[next]) {
                continue;
            }
            visit[next] = true;

            for (int i = 0; i < graph[next].size(); i++) {
                Node nextNode = graph[next].get(i);
                int nextTarget = nextNode.next;
                int nextCost = nextNode.cost + cost;

                if (dist[nextTarget] > nextCost) {
                    dist[nextTarget] = nextCost;
                    queue.add(new Node(nextTarget, nextCost));
                }
            }

        }
    }
}
