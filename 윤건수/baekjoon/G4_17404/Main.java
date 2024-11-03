package baekjoon.G4_17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] dp;
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    static int MAX = 1000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int homeCount = Integer.parseInt(br.readLine());
        int[][] cost = new int[homeCount][3];

        for(int i = 0; i < homeCount; i++){
            String[] colorCost = br.readLine().split(" ");
            int redCost = Integer.parseInt(colorCost[RED]);
            int greenCost = Integer.parseInt(colorCost[GREEN]);
            int blueCost = Integer.parseInt(colorCost[BLUE]);
            cost[i][RED] = redCost;
            cost[i][GREEN] = greenCost;
            cost[i][BLUE] = blueCost;
        }

        int result = Integer.MAX_VALUE;
        for(int color = RED; color <= BLUE; color++){
            dp = new int[homeCount][3];
            for(int home = 0; home < homeCount; home++){
                if(home == 0) {
                    dp[home][RED] = color == RED ? cost[home][RED] : MAX;
                    dp[home][GREEN] = color == GREEN ? cost[home][GREEN] : MAX;
                    dp[home][BLUE] = color == BLUE ? cost[home][BLUE] : MAX;
                }else if(home == homeCount - 1){
                    dp[home][RED] += color == RED ? MAX * 1000 : Math.min(dp[home - 1][BLUE], dp[home-1][GREEN]) + cost[home][RED];
                    dp[home][BLUE] += color == BLUE ? MAX * 1000 : Math.min(dp[home - 1][RED], dp[home-1][GREEN]) + cost[home][BLUE];
                    dp[home][GREEN] += color == GREEN ? MAX * 1000 :  Math.min(dp[home - 1][BLUE], dp[home-1][RED]) + cost[home][GREEN];
                }else{
                    dp[home][RED] += Math.min(dp[home - 1][BLUE], dp[home-1][GREEN]) + cost[home][RED];
                    dp[home][BLUE] += Math.min(dp[home - 1][RED], dp[home-1][GREEN]) + cost[home][BLUE];
                    dp[home][GREEN] += Math.min(dp[home - 1][BLUE], dp[home-1][RED]) + cost[home][GREEN];
                }
            }
            result = Math.min(result, dp[homeCount-1][0]);
            result = Math.min(result, dp[homeCount-1][1]);
            result = Math.min(result, dp[homeCount-1][2]);
        }
        System.out.println(result);
    }

}
