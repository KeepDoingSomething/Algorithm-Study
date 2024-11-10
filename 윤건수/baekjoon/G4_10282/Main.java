package baekjoon.G4_10282;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine());
        while (testcase-- > 0) {
            solution();
        }

        bw.flush();
        bw.close();
    }

    private static void solution() throws IOException {
        String[] params = br.readLine().split(" ");
        int computerCnt = Integer.parseInt(params[0]);
        int dependencyCnt = Integer.parseInt(params[1]);
        int hackStart = Integer.parseInt(params[2]);
        ArrayList<int[]>[] dep = new ArrayList[computerCnt + 1];
        for(int i = 1; i <= computerCnt; i++){
            dep[i] = new ArrayList<>();
        }

        for (int i = 0; i < dependencyCnt; i++) {
            String[] depInfo = br.readLine().split(" ");
            int computerA = Integer.parseInt(depInfo[0]);
            int computerB = Integer.parseInt(depInfo[1]);
            int hackedTime = Integer.parseInt(depInfo[2]);

            dep[computerB].add(new int[]{computerA, hackedTime});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((com1, com2) -> {
            int stackHackTime1 = com1[1];
            int stackHackTime2 = com2[1];
            return stackHackTime1 - stackHackTime2;
        });

        int hackedComputerCnt = 0;
        int time = 0;
        boolean[] visit = new boolean[computerCnt + 1];
        pq.add(new int[]{hackStart, 0, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curComputer = cur[0];
            int curStackTime = cur[1];
            if(visit[curComputer]) continue;
            visit[curComputer] = true;
            hackedComputerCnt++;
            time = Math.max(time, curStackTime);

            for(int[] depInfo : dep[curComputer]){
                int nextComputer = depInfo[0];
                int hackTime = depInfo[1];
                if(!visit[nextComputer]){
                    pq.add(new int[]{nextComputer, curStackTime + hackTime});
                }
            }
        }

        bw.append(hackedComputerCnt + " " + time);
        bw.newLine();
    }

}
