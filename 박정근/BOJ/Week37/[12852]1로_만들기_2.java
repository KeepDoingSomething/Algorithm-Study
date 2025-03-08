/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.04(Tue)
 * Runtime   : 240ms
 * Memory    : 44300KB
 * Algorithm : Dynamic Programming
 */

// >> 첫 번째 풀이 ( DP )
// 역추적 DP로 풀이했습니다. (Solve Time : 0h 16m)

// n부터 시작해서 1까지 가능한 연산을 했을 때 최소 연산 횟수를 구하면서, 이를 어떤 연산으로 했는지를 기록하며 DP 테이블을 만들었습니다.
// 역추적 편의를 위해, 역으로 1부터 시작해서 값을 구했습니다.

import java.io.*;
import java.util.*;

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][2];
        for (int i = 1; i < n+1; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = -1;
        }
        dp[1][0] = 0;

        for (int i = 1; i < n; i++) {
            // 3으로 곱하는 연산이 연산 횟수가 더 적다면
            if (i * 3 <= n && dp[i][0] + 1 < dp[i * 3][0]) {
                dp[i * 3][0] = dp[i][0] + 1;
                dp[i * 3][1] = i;
            }

            // 2로 곱하는 연산이 연산 횟수가 더 적다면
            if (i * 2 <= n && dp[i][0] + 1 < dp[i * 2][0]) {
                dp[i * 2][0] = dp[i][0] + 1;
                dp[i * 2][1] = i;
            }

            // 1을 더하는 연산이 연산 횟수가 더 적다면
            if (dp[i][0] + 1 < dp[i + 1][0]) {
                dp[i + 1][0] = dp[i][0] + 1;
                dp[i + 1][1] = i;
            }
        }

        // 최소 연산 횟수
        System.out.println(dp[n][0]);

        // 역추적
        System.out.print(n);
        int now = dp[n][1];
        
        while (now != -1) {
            System.out.print(" " + now);
            now = dp[now][1];
        }
    }
}
