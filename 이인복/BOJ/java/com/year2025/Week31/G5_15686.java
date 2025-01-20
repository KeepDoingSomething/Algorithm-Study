/**
 * Author    : Lee In Bok
 * Date      : 2025.01.19(Sun)
 * Runtime   : 296 ms
 * Memory    : 25976 KB
 * Algorithm : Backtracking
 */

package com.year2025.Week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_15686 {

    public static int N;
    public static int M;
    public static int[][] map;
    public static Store[] comb;
    public static List<Store> storeList;
    public static List<House> houseList;
    public static boolean[] visited;
    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        comb = new Store[M];

        storeList = new ArrayList<>();
        houseList = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                int type = Integer.parseInt(st.nextToken());

                if(type == 1){
                    houseList.add(new House(i, j));
                } else if(type == 2){
                    storeList.add(new Store(i, j));
                }
            }
        }

        visited = new boolean[storeList.size()];
        backTracking(0, 0);

        System.out.println(ans);
    }

    public static void backTracking(int storeIdx, int comIdx){
        if(comIdx == M){
            int sum = 0;

            for(Store s : comb){
                for(int i = 0; i < houseList.size(); i++){
                    House h = houseList.get(i);
                    int dist = Math.abs(s.x - h.x) + Math.abs(s.y - h.y);

                    if(h.min > dist){
                        h.min = dist;
                    }
                }
            }

            for(int idx = 0; idx < houseList.size(); idx++){
                sum += houseList.get(idx).min;
            }

            sum = houseList.stream()
                           .map(House::getMin)
                           .mapToInt(Integer::intValue)
                           .sum();

            if(ans > sum){
                ans = sum;
            }

            houseList.forEach(House::setMin);

            return;
        }

        for(int i = storeIdx; i < storeList.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                comb[comIdx] = storeList.get(i);
                backTracking(i + 1, comIdx + 1);
                visited[i] = false;
            }
        }
    }

    static class Store {
        int x;
        int y;
        int dist;

        public Store(int x, int y) {
            this.x = x;
            this.y = y;
            this.dist = 0;
        }
    }

    static class House {
        int x;
        int y;
        int min;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
            this.min = Integer.MAX_VALUE;
        }

        public int getMin() {
            return min;
        }

        public void setMin() {
            this.min = Integer.MAX_VALUE;
        }
    }
}