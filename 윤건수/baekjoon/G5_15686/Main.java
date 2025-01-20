package baekjoon.G5_15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final String SHOP = "2";
    private static final String HOME = "1";
    private static int bestDistance;
    static int MAX_SHOP;
    static ArrayList<int[]> shops;
    static ArrayList<int[]> homes;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] franchiseInfo = br.readLine().split(" ");
        int n = Integer.parseInt(franchiseInfo[0]);
        MAX_SHOP = Integer.parseInt(franchiseInfo[1]);
        String[][] map = new String[n][n];
        shops = new ArrayList<>();
        homes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                String pos = line[j];
                map[i][j] = pos;
                if(pos.equals(SHOP)){
                    shops.add(new int[]{i, j});
                }else if(pos.equals(HOME)){
                    homes.add(new int[]{i, j});
                }
            }
        }

        bestDistance = Integer.MAX_VALUE;
        LinkedList<int[]> visitedShop = new LinkedList<>();
        dfs(visitedShop, -1);

        System.out.println(bestDistance);
    }

    private static void dfs(List<int[]> visitedShop, int lastShopIdx) {
        if(visitedShop.size() == MAX_SHOP){
            checkMinDist(visitedShop);
            return;
        }

        for(int i = lastShopIdx + 1; i < shops.size(); i++){
            int[] nextShop = shops.get(i);
            visitedShop.add(nextShop);
            dfs(visitedShop, i);
            visitedShop.remove(visitedShop.size() - 1);
        }
    }

    private static void checkMinDist(List<int[]> visitedShop) {
        int totalDist = 0;
        for (int i = 0; i < homes.size(); i++) {
            int[] home = homes.get(i);
            int chickDist = Integer.MAX_VALUE;
            for (int j = 0; j < visitedShop.size(); j++) {
                int[] shop = visitedShop.get(j);
                int dist = Math.abs(home[0] - shop[0]) + Math.abs(home[1] - shop[1]);
                chickDist = Math.min(chickDist, dist);
            }

            totalDist += chickDist;
        }

        bestDistance = Math.min(totalDist, bestDistance);
    }


}
