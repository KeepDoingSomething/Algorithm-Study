package programmers.LV2_12987;

import java.util.*;

public class Solution {

    boolean[] visited;
    int[] minTime;
    ArrayList[] toRoad;
    int INF = 500001;

    public int solution(int N, int[][] road, int K) {
        minTime = new int[N + 1];
        visited = new boolean[N + 1];
        toRoad = new ArrayList[N + 1];

        for(int i = 0; i < toRoad.length; i++){
            toRoad[i] = new ArrayList<int[]>();
            minTime[i] = INF;
        }

        for(int[] connect :  road){
            int town1 = connect[0];
            int town2 = connect[1];
            int dist = connect[2];

            toRoad[town1].add(new int[]{town2, dist});
            toRoad[town2].add(new int[]{town1, dist});
        }

        minTime[1] = 0;
        dijkstra(1);

        int answer = 0;
        for(int dist : minTime){
            if(dist <= K) answer++;
        }

        return answer;
    }

    private void dijkstra(int visit){
        visited[visit] = true;
        int curDist = minTime[visit];
        ArrayList<int[]> targerTown = toRoad[visit];
        for(int[] dest : targerTown){
            int town = dest[0];
            int dist = dest[1];
            if(curDist + dist < minTime[town]){
                minTime[town] = curDist + dist;
            }
        }

        int nextIdx = 51;
        int min = INF;
        for(int i = 1; i < minTime.length; i++){
            if(!visited[i] && min > minTime[i]){
                nextIdx = i;
                min = minTime[i];
            }
        }

        if(nextIdx != 51) dijkstra(nextIdx);
    }

}