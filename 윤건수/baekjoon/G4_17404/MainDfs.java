package baekjoon.G4_17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainDfs {

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    static Home[] homeArr;
    static int lastHomeIdx;
    static int minCost;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int homeCount = Integer.parseInt(br.readLine());
        homeArr = new Home[homeCount];
        lastHomeIdx = homeCount - 1;
        for(int i = 0; i < homeCount; i++){
            String[] cost = br.readLine().split(" ");
            int redCost = Integer.parseInt(cost[RED]);
            int greenCost = Integer.parseInt(cost[GREEN]);
            int blueCost = Integer.parseInt(cost[BLUE]);
            homeArr[i] = new Home(redCost, greenCost, blueCost);
        }

        minCost = Integer.MAX_VALUE;
        dfs(-1, 0);

        System.out.println(minCost);
    }

    private static void dfs(int homeIdx, int sumCost){
        if(homeIdx == lastHomeIdx){
            minCost = Math.min(minCost, sumCost);
            return;
        }

        int nextIdx = homeIdx + 1;
        Home nextHome = homeArr[nextIdx];
        for(int i = RED; i <= BLUE; i++){
            if (isPossibleColor(nextIdx, i)) {
                nextHome.color = i;
                sumCost += nextHome.colorCost[i];
                dfs(nextIdx, sumCost);
                sumCost -= nextHome.colorCost[i];
            }
        }
    }

    private static boolean isPossibleColor(int homeIdx, int color) {
        if(homeIdx == 0) return true;
        boolean isPossible = true;
        if(homeIdx == lastHomeIdx){
            isPossible = homeArr[0].color != color;
        }
        return isPossible && homeArr[homeIdx - 1].color != color;
    }

    private static class Home{
        int color;
        int[] colorCost;

        Home(int redCost, int greenCost, int blueCost){
            colorCost = new int[3];
            colorCost[0] = redCost;
            colorCost[1] = greenCost;
            colorCost[2] = blueCost;
        }
    }

}
