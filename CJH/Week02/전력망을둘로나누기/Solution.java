package programmers.level2.전력망을둘로나누기;

import java.util.ArrayList;

public class Solution {
    static ArrayList<Integer>[] A;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        A = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];
            A[u].add(v);
            A[v].add(u);
        }


        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            boolean[] visited = new boolean[n + 1];
            A[v1].remove(Integer.valueOf(v2));
            A[v2].remove(Integer.valueOf(v1));

            int cnt1 = DFS(v1, visited);
            answer = Math.min(answer, Math.abs(cnt1 - (n - cnt1)));

            A[v1].add(v2);
            A[v2].add(v1);
        }

        return answer;
    }

    private int DFS(int v, boolean[] visited) {
        visited[v] = true;
        int cnt = 1;
        for (Integer i : A[v]) {
            if (!visited[i]) {
                cnt += DFS(i, visited);
            }
        }
        return cnt;
    }
}
