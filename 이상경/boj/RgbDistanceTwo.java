/**
 * Author    : 이상경
 * Date      : 2024.11.10
 * Runtime   : 228 ms
 * Memory    : 22076 KB
 * Algorithm : DP
 */

import java.util.Scanner;

public class RgbDistanceTwo {

    public static void main(String[] args) {
        int[][] cost = new int[1001][3];
        int result = Integer.MAX_VALUE;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            cost[i][0] = sc.nextInt();
            cost[i][1] = sc.nextInt();
            cost[i][2] = sc.nextInt();
        }

        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[1001][3];

            for (int j = 0; j < 3; j++) {
                if (j == firstColor) {
                    dp[1][j] = cost[1][j];
                } else {
                    dp[1][j] = 1000001;
                }
            }

            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
            }

            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (firstColor != lastColor) {
                    result = Math.min(result, dp[n][lastColor]);
                }
            }
        }

        System.out.println(result);
    }
}
