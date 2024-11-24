/**
 * Author    : 김현정
 * Date      : 2024.09.05(목)
 * Runtime   :
 * Memory    :
 * Algorithm : [programmers]DFS&BFS - LV3_43162 네트워크
 */

package dfs_bfs;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(computers, i, visited);
                answer++;
            }
        }
        return answer;
    }

    boolean[] dfs(int[][] computers, int i, boolean[] visited) {
        visited[i] = true;

        for(int j = 0; j < computers.length; j++) {
            if(i != j && !visited[j] && computers[i][j] == 1){
                visited = dfs(computers, j, visited);
            }
        }
        return visited;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[][] computers = {{1, 1, 0},{1, 1, 0},{0, 0, 1}};
        int answer = solution.solution(n, computers);
        System.out.println(answer);
    }
}
