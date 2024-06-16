package baekjoon.G4_1922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 네트워크 연결 (Gold 4)
// https://www.acmicpc.net/problem/1922

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computerNum = Integer.parseInt(br.readLine());
        int connectNum = Integer.parseInt(br.readLine());
        ArrayList<Connect>[] connects = new ArrayList[computerNum + 1];
        for(int i = 1; i < connects.length; i++){
            connects[i] = new ArrayList<>();
        }

        String line = null;
        while((line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            int com1 = Integer.parseInt(st.nextToken());
            int com2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(com1 == com2) continue;
            connects[com1].add(new Connect(com2, cost));
            connects[com2].add(new Connect(com1, cost));
        }

        int result = prim(1, computerNum, connects);
        System.out.println(result);
    }

    private static int prim(int start, int computerNum, ArrayList<Connect>[] connects){
        PriorityQueue<Connect> queue = new PriorityQueue<>();
        queue.add(new Connect(start, 0));
        boolean[] visited = new boolean[computerNum + 1];
        int sumCost = 0;

        while(!queue.isEmpty()){
            Connect connect = queue.poll();
            int to = connect.to;
            int cost = connect.cost;

            if(visited[to]) continue;

            sumCost += cost;
            visited[to] = true;

            for(Connect next : connects[to]){
                if(!visited[next .to]){
                    queue.add(next);
                }
            }
        }

        return sumCost;
    }

    private static class Connect implements Comparable<Connect>{
        int to;
        int cost;

        Connect(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connect o) {
            return this.cost - o.cost;
        }
    }

}
