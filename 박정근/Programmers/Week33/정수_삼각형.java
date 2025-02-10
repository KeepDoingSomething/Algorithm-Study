/**
 * Author    : Park Jeong Geun
 * Date      : 2025.02.03(Mon)
 * Runtime   : 14.28ms
 * Memory    : 66.1MB
 * Algorithm : Dynamic Programming
 */

// >> 첫 번째 풀이 ( DP )
// DP로 풀이했습니다. (Solve Time : 0h 06m)
// 간단한 점화식의 DP 문제입니다. 삼각형 형태를 2차원 배열로 표현하는 것이 포인트입니다.

import java.util.Arrays;
class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], 0);
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.max(
                    (j > 0) ? (dp[i-1][j-1]) : 0, // 왼쪽
                    (j < i) ? (dp[i-1][j]) : 0) // 오른쪽
                    + triangle[i][j];
            }
        }
        
        
        int answer = 0;
        for (int e : dp[n-1]) answer = Math.max(answer, e);
        return answer;
    }
}
