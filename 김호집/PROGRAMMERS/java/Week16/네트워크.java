/**
 * Author    : HojipKim
 * Date      : 2024.09.07(Sat)
 * Runtime   : 0.79 ms
 * Memory    : 77.5 MB
 * Algorithm : BFS, DFS
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public static ArrayList<Integer>[] arr;
    public static boolean isVisited[];

    public int solution(int n, int[][] computers) {
        int answer = 0;

        arr = new ArrayList[n+1];
        isVisited = new boolean[n+1];

        for(int i =0; i < computers.length; i++){
            ArrayList tmp = new ArrayList<>();
            for(int j = 0; j< computers[i].length; j++){
                if (i == j){
                    continue;
                }

                if(computers[i][j] == 1){
                    tmp.add(j+1);
                }
            }
            arr[i+1] = tmp;
        }
        for(int i = 1; i <= n; i++){
            if(!isVisited[i]){
                answer++;
                bfs(i);
            }

        }

        return answer;
    }

    public void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        isVisited[start] = true;
        queue.add(start);
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for (int i = 0; i < arr[cur].size(); i++) {
                if(!isVisited[arr[cur].get(i)]){
                    isVisited[arr[cur].get(i)] = true;
                    queue.add(arr[cur].get(i));
                }
            }
        }
    }
}