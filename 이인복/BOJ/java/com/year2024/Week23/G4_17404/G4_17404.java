/**
 * Author    : Lee In Bok
 * Date      : 2024.11.03(Sun)
 * Runtime   : 136 ms
 * Memory    : 15812 KB
 * Algorithm : Dynamic Programming
 */

package com.year2024.Week23.G4_17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G4_17404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int INF = 100_000_000;
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N + 1][3];
        int[][] dp = new int[N + 1][3];
        int ans = INF;

        for(int i = 1; i <= N; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        }

        // 0: Red, 1: Green, 2: Blue (첫 집의 색)
        for(int color = 0; color < 3; color++) {
            for(int i = 0; i < 3; i++) {
                dp[1][i] = color == i ? cost[1][i] : INF;
            }

            for(int i = 2; i <= N; i++) {
                int prevHouse = i - 1;

                dp[i][0] = Math.min(dp[prevHouse][1], dp[prevHouse][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[prevHouse][0], dp[prevHouse][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[prevHouse][0], dp[prevHouse][1]) + cost[i][2];
            }

            for(int i = 0; i < 3; i++) {
                if(i == color) continue;
                ans = Math.min(ans, dp[N][i]);
            }
        }

        System.out.println(ans);
    }
}