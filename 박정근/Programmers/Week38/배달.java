/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.11(Tue)
 * Runtime   : 6.97ms
 * Memory    : 96MB
 * Algorithm : Floyd-Warshall
 */

// >> 첫 번째 풀이 ( Floyd-Warshall )
// 플로이드 워셜로 풀이했습니다. (Solve Time : 0h 17m)

// 그래프 안에서 1번 마을부터 다른 마을까지의 최단 거리를 구하는 문제입니다.
// 정점의 개수가 적으므로 플로이드 워셜 풀이가 가능합니다.
// 두 마을 a, b를 연결하는 도로는 여러 개가 있을 수 있습니다. -> 이를 주의하면 쉽게 정답을 맞을 수 있는 문제입니다.

import java.util.Arrays;
class Solution {
    public static int MAX_NUM = 10000 * 50 + 1;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        int[][] dp = new int[N+1][N+1]; // DP 선언 및 초기화
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(dp[i], MAX_NUM);
            dp[i][i] = 0;
        }
        
        // 도로 비용 등록 ( + 두 마을을 연결하는 도로는 여러 개가 있을 수 있으므로 min 연산 사용 )
        for (int i = 0; i < road.length; i++) {
            dp[road[i][0]][road[i][1]] = Math.min(dp[road[i][0]][road[i][1]], road[i][2]);
            dp[road[i][1]][road[i][0]] = Math.min(dp[road[i][1]][road[i][0]], road[i][2]);
        }
        
        // Floyd-Warshall
        for (int x = 1; x <= N; x++) {
            for (int u = 1; u <= N; u++) {
                for (int v = 1; v <= N; v++) dp[u][v] = Math.min(dp[u][v], dp[u][x] + dp[x][v]);
            }
        }
        
        // 1번 마을과의 거리가 K 이하인 마을 개수 반환
        for (int i = 1; i <= N; i++) {
            if (dp[1][i] <= K) answer++;
        }

        return answer;
    }
}
