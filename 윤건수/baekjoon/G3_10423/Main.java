package baekjoon.G3_10423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int city = Integer.parseInt(line[0]);
        int cableNum = Integer.parseInt(line[1]);
        int factory = Integer.parseInt(line[2]);
        int[] startCities = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        ArrayList<Cable>[] cityCables = new ArrayList[city + 1];
        for(int i = 0; i < city + 1; i++) {
            cityCables[i] = new ArrayList<>();
        }

        for(int i = 0; i < cableNum; i++){
            String[] nodeInfo = br.readLine().split(" ");
            int city1 = Integer.parseInt(nodeInfo[0]);
            int city2 = Integer.parseInt(nodeInfo[1]);
            int cableCost = Integer.parseInt(nodeInfo[2]);
            cityCables[city1].add(new Cable(city2, cableCost));
            cityCables[city2].add(new Cable(city1, cableCost));
        }

        PriorityQueue<Cable> pq = new PriorityQueue<>((cable1, cable2) -> cable1.cost - cable2.cost);
        boolean[] visited = new boolean[city + 1];
        for(int startCity : startCities){
            pq.addAll(cityCables[startCity]);
            visited[startCity] = true;
        }

        int result = 0;
        while(!pq.isEmpty()){
            Cable cable = pq.poll();
            if(visited[cable.toCity]) continue;
            visited[cable.toCity] = true;
            pq.addAll(cityCables[cable.toCity]);
            result += cable.cost;
        }

        System.out.println(result);
    }

    private static class Cable{
        int toCity;
        int cost;

        Cable(int toCity, int cost){
            this.toCity = toCity;
            this.cost = cost;
        }
    }

}
