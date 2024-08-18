package baekjoon.G4_1753;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        int infinite = 999999999;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        int[] range = new int[n+1];
        boolean[] visited = new boolean[n+1];
        ArrayList<Node>[] node = new ArrayList[n+1];

        for(int i=0; i<=n; i++){
            node[i] = new ArrayList<>();
            range[i] = infinite;
        }
        range[start]=0;
        visited[start] = true;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            node[s].add(new Node(e, v));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int current = now.target;
            for(int i=0; i<node[current].size(); i++){
                Node tmp = node[current].get(i);
                int next = tmp.target;
                int value = tmp.val;
                if(range[next] > range[current]+value){
                    range[next] = range[current]+value;
                    pq.add(new Node(next, range[next]));
                }
            }
        }

        for(int i=1; i<=n; i++){
            if(range[i]!=infinite){
                bw.write(range[i] + "");
                bw.newLine();
            }else{
                bw.write("INF");
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class Node implements Comparable<Node>{

        int target;
        int val;

        Node(int target, int val){
            this.target=target;
            this.val=val;
        }

        public int compareTo(Node n){
            if(this.val > n.val) return 1;
            else return -1;
        }
    }
}
