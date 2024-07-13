package programmers.level2.피로도;

public class Solution {

    private int answer;
    public boolean[] visited; // 방문 여부

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        dfs(0, k, dungeons);

        return answer;
    }

    private void dfs(int depth, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            // 방문하지 않은 상태 && 최소 필요 피로도 이상인 경우
            if (!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                dfs(depth + 1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, depth);
    }


}
