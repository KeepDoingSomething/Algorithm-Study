package programmers.level3.네트워크;

import java.util.ArrayList;

public class Solution {

    ArrayList<ArrayList<Integer>> network;
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n + 1];
        network = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            network.add(new ArrayList<>());
        }
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[0].length; j++) {
                   if (i != j && computers[i][j] == 1) {
                       network.get(i + 1).add(j + 1);
                   }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i);
            }
        }

        return answer;
    }

    private void dfs(int start) {
        visited[start] = true;

        for (Integer i : network.get(start)) {
            if (!visited[i]) {
                dfs(i);
            }
        }

    }
}
