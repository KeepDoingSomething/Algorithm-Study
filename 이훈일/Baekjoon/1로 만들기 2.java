package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        int[] trace = new int[N+1];
        for(int i = 2; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[1] = 0;
        for(int i = 2; i <= N; i++) {
            if(i % 3 == 0 && dp[i/3] + 1 < dp[i]) {
                dp[i] = dp[i/3] + 1;
                trace[i] = i/3;
            }

            if(i % 2 == 0 && dp[i/2] + 1 < dp[i]) {
                dp[i] = dp[i/2] + 1;
                trace[i] = i/2;
            }

            if(dp[i-1] + 1 < dp[i]) {
                dp[i] = dp[i-1] + 1;
                trace[i] = i-1;
            }
        }

        StringBuilder sb = new StringBuilder();
        int num = N;
        sb.append(num);
        for(int i = 0; i < dp[N]; i++) {
            num = trace[num];
            sb.append(" " + num);
        }

        System.out.println(dp[N]);
        System.out.println(sb.toString());
    }
}
