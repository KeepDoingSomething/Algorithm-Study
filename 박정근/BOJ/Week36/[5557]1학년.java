/**
 * Author    : Park Jeong Geun
 * Date      : 2025.02.26(Wed)
 * Runtime   : 104ms
 * Memory    : 14092KB
 * Algorithm : Dynamic Programming
 */

// >> 첫 번째 풀이 ( DP )
// DP로 풀이했습니다. (Solve Time : 0h 20m)
// 덧셈과 뺄셈을 수행하는 모든 경우를 탐색하려고 하면 시간 초과가 발생합니다. DP로 경우의 수를 빠르게 구했습니다.
// dp[i][j] : i번째 수까지 계산했을 때 수 j가 결과로 나오는 경우의 수
// dp[i][j] = dp[i-1][j - arr[i]] (단, j - arr[i] >= 0) + dp[i-1][j + arr[i]] (단, j + arr[i] <= 20)

import java.io.*;
import java.util.*;

class Freshman {
    int n;
    int[] arr;
    public Freshman(int n, int[] arr) {
        this.n = n;
        this.arr = new int[n];
        for (int i = 0; i < n; i++) this.arr[i] = arr[i];
    }

    public long solve() {
        long[][] dp = new long[n-1][21];
        dp[0][arr[0]] = 1;

        for (int i = 1; i < n-1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (j - arr[i] >= 0) dp[i][j] += dp[i-1][j - arr[i]];
                if (j + arr[i] <= 20) dp[i][j] += dp[i-1][j + arr[i]];
            }
        }

        // n - 2번째 수까지 계산했을 때 arr[n-1]가 결과가 나오는 경우의 수
        return dp[n-2][arr[n-1]];
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Freshman fm = new Freshman(n, arr);
        System.out.println(fm.solve());
    }
}
