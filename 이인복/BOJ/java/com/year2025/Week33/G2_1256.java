/**
 * Author    : Lee In Bok
 * Date      : 2025.02.01(Sat)
 * Runtime   : 104 ms
 * Memory    : 14400 KB
 * Algorithm : Dynamic Programming
 */

package com.year2025.Week33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_1256 {

    public static int[][] dp;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int max = N + M + 1;

        dp = new int[max][max];
        dp(N, M);

        if(dp[N][M] < K) {
            System.out.println("-1");
        } else {
            findStr(N, M, K);
            System.out.println(sb.toString());
        }
    }

    /**
     * dp[3][3] = dp[2][3] + dp[3][2]
     * -> dp[2][3] + a 로 시작하는 문자열 + 남은 문자로 가능한 조합
     * -> dp[3][2] + z 로 시작하는 문자열 + 남은 문자로 가능한 조합
     */
    public static int dp(int a, int z) {
        if(a == 0 || z == 0) return 1;
        if(dp[a][z] != 0) return dp[a][z];

        return dp[a][z] = Math.min(dp(a - 1, z) + dp(a, z - 1), 1_000_000_001);
    }

    public static void findStr(int a, int z, int k) {
        // a 는 소진되었기 때문에 남은 공간 z 로 채우면 됨
        if(a == 0) {
            for(int i = 0; i < z; i++) {
                sb.append("z");
            }
            return;
        }

        if(z == 0) {
            for(int i = 0; i < a; i++) {
                sb.append("a");
            }
            return;
        }

        int startA = dp(a - 1, z);  // a 로 시작 + 남은 문자 조합

        if(startA < k) {  // a 로 시작 불가능
            sb.append("z");
            findStr(a, z - 1, k - startA);  // z 로 시작, k 에서 a 로 시작할 수 있는 경우 제거
        } else {
            sb.append("a");
            findStr(a - 1, z, k);  // a 로 시작 가능하면 또 a 로 시작 (오름차순 이라서 a 는 버릴 필요 없음)
        }
    }
}
