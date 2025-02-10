package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1256 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[201][201];
        for(int i = 0; i < 201; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for(int i = 1; i < 201; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i][j] = dp[i-1][j] + dp[i-1][j-1];

                if(dp[i][j] > 1000000000) {
                    dp[i][j] = 1000000001;
                }
            }
        }

        if(dp[N+M][M] < K) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            while(N != 0 || M != 0) {
                if(K <= dp[N+M-1][M]) {
                    sb.append("a");
                    N--;
                } else {
                    sb.append("z");
                    K -= dp[N+M-1][M];
                    M--;
                }
            }
            System.out.println(sb.toString());
        }
    }
}
