package programmers.LV2_86971;

import java.util.*;

public class Solution {
    public int solution(int n, int[][] wires) {
        int totalNetwork = n;
        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < wires.length; i++){
            // 연결이 끊긴 한곳만 체크하면 나머지도 알수있 음
            int disconnectNetwork = wires[i][0];
            ArrayList[] connectInfo = createConnectInfo(i, n, wires);
            boolean[] visited = new boolean[n + 1];
            int linkCount = 0;

            Queue<Integer> que = new LinkedList<>();
            que.add(disconnectNetwork);

            while(!que.isEmpty()){
                int targetNetwork = que.poll();
                linkCount++;
                visited[targetNetwork] = true;
                ArrayList<Integer> targetConnect = connectInfo[targetNetwork];
                for(Integer connectNetwork : targetConnect){
                    if(!visited[connectNetwork]){
                        que.add(connectNetwork);
                    }
                }
            }

            int anotherLinkCount = (totalNetwork - linkCount);
            int diff = linkCount > anotherLinkCount
                    ? linkCount - anotherLinkCount
                    : anotherLinkCount - linkCount;

            answer = Math.min(answer, diff);
        }

        return answer;
    }

    private ArrayList<Integer>[] createConnectInfo(int disconnectIdx, int n, int[][] wires){
        // 0 번인덱스 미사용
        ArrayList<Integer>[] connect = new ArrayList[n+1];
        for(int i = 1; i < n + 1; i++){
            connect[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < wires.length; i++){
            if(i == disconnectIdx) continue;
            int network1 = wires[i][0];
            int network2 = wires[i][1];
            connect[network1].add(network2);
            connect[network2].add(network1);
        }

        return connect;
    }
}