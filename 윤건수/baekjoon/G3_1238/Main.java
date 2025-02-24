package baekjoon.G3_1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int studentNum = info[0];
        int roadCnt = info[1];
        int target = info[2];

        int TIME_INF = 1001 * 100;
        int[][] dp = new int[studentNum + 1][studentNum + 1];
        for (int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                if(i == j) dp[i][j] = 0;
                else dp[i][j] = TIME_INF;
            }
        }

        String line;
        while ((line = br.readLine()) != null) {
            int[] road = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = road[0];
            int to = road[1];
            int time = road[2];
            dp[from][to] = time;
        }

        for (int k = 1; k < dp.length; k++) {
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp.length; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i < dp.length; i++) {
            maxTime = Math.max(maxTime, dp[i][target] + dp[target][i]);
        }
        System.out.println(maxTime);
    }

}
