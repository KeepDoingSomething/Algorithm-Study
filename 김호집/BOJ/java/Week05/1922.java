/**
 * Author    : HojipKim
 * Date      : 2024.06.15(Sat)
 * Runtime   : 460 ms
 * Memory    : 47616 KB
 * Algorithm : 그래프 이론, 최소 스패닝 트리
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Edge {
    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

}

public class Main {

    static int[] parent;
    static ArrayList<Edge> edgeArrayList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        edgeArrayList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeArrayList.add(new Edge(start, end, weight));
        }

        parent = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeArrayList, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight-o2.weight;
            }
        });

        int ans = 0;

        for (int i = 0; i < edgeArrayList.size(); i++) {
            Edge edge = edgeArrayList.get(i);

            if(find(edge.start) != find(edge.end)){
                ans += edge.weight;
                union(edge.start, edge.end);
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    public static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            parent[y] = x;
        }

    }
}