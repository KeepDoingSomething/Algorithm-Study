package baekjoon.G2_10473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double[] startPoint = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        double[] endPoint = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        int cannonCnt = Integer.parseInt(br.readLine());
        double[][] points = new double[cannonCnt + 2][2];
        points[0] = startPoint;
        points[cannonCnt + 1] = endPoint;

        for (int i = 1; i <= cannonCnt; i++) {
            double[] cannonPoint = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            points[i] = cannonPoint;
        }

        final double walkSpeed = 5;
        final double cannonSpeed = 25;
        final double cannonMove = 50;
        final double cannonSecond = cannonMove / cannonSpeed;

        double[][] graph = new double[cannonCnt + 2][cannonCnt + 2];

        for (int i = 0; i < points.length - 1; i++) { // 마지막 지점에서 출발하는 경우는 체크할 필요 없음
            double[] from = points[i];
            for(int j = 1; j < points.length; j++) {
                if(i == j) continue;
                double[] to = points[j];
                double dist = calcDistance(from, to);
                if(i == 0){
                    // 시작지점에서 출발시 항상 걸어서가야함
                    graph[i][j] =  dist / walkSpeed;
                }else{
                    double walk = dist / walkSpeed;
                    dist = Math.abs(dist - cannonMove);
                    double cannonWalk = cannonSecond + (dist / walkSpeed);

                    graph[i][j] = Math.min(walk, cannonWalk) ;
                }
            }
        }

        PriorityQueue<double[]> pq = new PriorityQueue<>((second1, second2) -> {
            if(second1[1] - second2[1] > 0) return 1;
            else if(second1[1] == second2[1]) return 0;
            else return -1;
        });

        double[] startGraph = graph[0];
        for(int i = 0; i < startGraph.length; i++) {
            pq.add(new double[]{(double) i, startGraph[i]});
        }

        boolean[] visited = new boolean[cannonCnt + 2];
        double[] toSecond = new double[cannonCnt + 2];
        Arrays.fill(toSecond, Double.MAX_VALUE);
        toSecond[0] = 0;

        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            int curIdx = (int) cur[0];
            double curDist = cur[1];
            if(visited[curIdx]) continue;
            visited[curIdx] = true;

            double[] curGraph = graph[curIdx];
            for(int next = 0; next < curGraph.length; next++) {
                if(next == curIdx || visited[next]) continue;
                double nextDist = curDist + curGraph[next];
                if(nextDist < toSecond[next]){
                    toSecond[next] = nextDist;
                    pq.add(new double[]{next, nextDist});
                }
            }
        }

        System.out.println(toSecond[cannonCnt + 1]);
    }

    static double calcDistance(double[] from, double[] to) {
        return Math.sqrt(Math.pow(to[0] - from[0], 2) + Math.pow(to[1] - from[1], 2));
    }

}
