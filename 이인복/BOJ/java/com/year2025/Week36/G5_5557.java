/**
 * Author    : Lee In Bok
 * Date      : 2025.02.24(Mon)
 * Runtime   : 14132 ms
 * Memory    : 104 KB
 * Algorithm : Dynamic Programming
 */
package com.year2025.Week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G5_5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[N - 1];
        long[][] dp = new long[N - 1][21];

        for(int i = 0; i < N - 1; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int res = Integer.parseInt(st.nextToken());

        dp[0][seq[0]] = 1;

        for(int i = 0; i < N - 2; i++) {
            for(int j = 0; j < 21; j++) {
                if(dp[i][j] == 0) continue;

                int plus = j + seq[i + 1];
                int minus = j - seq[i + 1];

                if(plus <= 20) {
                    dp[i + 1][plus] += dp[i][j];
                }

                if(minus >= 0) {
                    dp[i + 1][minus] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N - 2][res]);
    }
}
