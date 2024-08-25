/**
 * Author    : Kang San Ah
 * Date      : 2024.08.13(Wed)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : Greedy Algorithm, Dijkstra
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
    public int vex;
    public int cost;

    public Edge(int vex, int cost){
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {
    private static int[] dis;
    static ArrayList<ArrayList<Edge>> graph;
    static int V,E,K;

    public void solution(int v){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(v, 0));
        dis[v] = 0;

        while (!pq.isEmpty()){
            Edge tmp = pq.poll();
            int now = tmp.vex;
            int nowCost = tmp.cost;
            if (nowCost > dis[now]) continue;
            for (Edge ob : graph.get(now)){
                if (dis[ob.vex] > nowCost+ob.cost){
                    dis[ob.vex] = nowCost + ob.cost;
                    pq.offer(new Edge(ob.vex , nowCost + ob.cost));
                }
            }
        }
    }

    public static void main(String[] args){

        Main main= new Main();

        graph = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        V = scanner.nextInt();
        E = scanner.nextInt();
        K = scanner.nextInt();

        for (int i = 0 ; i <= V ; i++){
            graph.add(new ArrayList<Edge>());
        }

        dis = new int[V+1];
        Arrays.fill(dis,Integer.MAX_VALUE);

        for (int i = 0 ; i < E ; i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            graph.get(u).add(new Edge(v,w));
        }

        main.solution(K);
        for (int i = 1 ; i <= V ; i ++){
            if (dis[i]!= Integer.MAX_VALUE) System.out.println(dis[i]);
            else System.out.println("INF");
        }
    }
}
