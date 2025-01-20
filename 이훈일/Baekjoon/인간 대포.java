package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon10473 {
    static HashMap<Integer, float[]> nodes = new HashMap<>();
    static int size;
    static float[][] graph;
    static float[] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        float[] start = new float[2];
        start[0] = Float.parseFloat(st.nextToken());
        start[1] = Float.parseFloat(st.nextToken());

        st = new StringTokenizer(br.readLine());
        float[] end = new float[2];
        end[0] = Float.parseFloat(st.nextToken());
        end[1] = Float.parseFloat(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        nodes.put(0, start);
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            float[] node = new float[2];
            node[0] = Float.parseFloat(st.nextToken());
            node[1] = Float.parseFloat(st.nextToken());
            nodes.put(i, node);
        }
        nodes.put(n+1, end);

        size = n+2;
        makeGraph();

        daijkstra();

        System.out.println(result[size-1]);
    }

    public static void makeGraph() {
        graph = new float[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                float x1 = nodes.get(i)[0];
                float y1 = nodes.get(i)[1];
                float x2 = nodes.get(j)[0];
                float y2 = nodes.get(j)[1];

                float dx = Math.abs(x1 - x2);
                float dy = Math.abs(y1 - y2);

                float dis = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                if(i == 0) {
                    graph[i][j] = dis / 5;
                } else {
                    graph[i][j] = Math.min((dis / 5), (Math.abs(dis - 50) / 5 + 2));
                }
            }
        }
    }

    public static void daijkstra() {
        float inf = Float.MAX_VALUE;
        result = new float[size];
        for(int i = 1; i < size; i++) {
            result[i] = inf;
        }

        visited = new boolean[size];
        PriorityQueue<float[]> queue = new PriorityQueue<>((a,b) -> Float.compare(a[1], b[1]));
        queue.add(new float[]{0, 0});
        while(!queue.isEmpty()) {
            float[] node = queue.poll();
            int num = (int) node[0];

            if(visited[num]) {
                continue;
            }
            visited[num] = true;

            for(int i = 0; i < size; i++) {
                if(result[i] >= result[num] + graph[num][i]) {
                    result[i] = result[num] + graph[num][i];
                    queue.add(new float[]{i, result[i]});
                }
            }
        }
    }
}
