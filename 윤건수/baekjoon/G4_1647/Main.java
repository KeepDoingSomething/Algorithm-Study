package baekjoon.G4_1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] params = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int homeCnt = params[0];
        int roadCnt = params[1];
        List<Road>[] homeRoads = new List[homeCnt + 1];
        for (int i = 0; i < homeRoads.length; i++) {
            homeRoads[i] = new ArrayList<>();
        }

        String line;
        while ((line = br.readLine()) != null) {
            int[] roadInfo = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = roadInfo[0];
            int to = roadInfo[1];
            int cost = roadInfo[2];

            homeRoads[from].add(new Road(from, to, cost));
            homeRoads[to].add(new Road(to, from, cost));
        }

        boolean[] visited = new boolean[homeCnt + 1];
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.addAll(homeRoads[1]);
        visited[1] = true;

        long totalCost = 0;
        long maxCost = 0;
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            if(visited[road.to]) continue;

            visited[road.to] = true;
            totalCost += road.cost;
            maxCost = Math.max(maxCost, road.cost);

            List<Road> nextRoads = homeRoads[road.to];
            nextRoads.forEach(next -> {
                if(!visited[next.to]){
                    pq.add(new Road(next.from, next.to, next.cost));
                }
            });
        }

        System.out.println(totalCost - maxCost);
    }

    private static class Road implements Comparable<Road>{
        int from;
        int to;
        int cost;

        Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return this.cost - o.cost;
        }
    }

}
