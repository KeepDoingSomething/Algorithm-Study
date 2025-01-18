/**
 * Author    : Lee In Bok
 * Date      : 2024.12.10(Tue)
 * Runtime   : 3284 ms
 * Memory    : 17836 KB
 * Algorithm : Backtracking
 */

package com.year2024.Week28.G4_9663;

import java.util.Scanner;

public class G4_9663 {

    public static int N;
    public static int ans = 0;
    public static boolean[] visited1;  // row 방문
    public static boolean[] visited2;  // 오-왼 대각 방문
    public static boolean[] visited3;  // 왼-오 대각 방문


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited1 = new boolean[N];
        visited2 = new boolean[N * 2 - 1];
        visited3 = new boolean[N * 2 - 1];

        backtrack(0);

        System.out.println(ans);
    }

    public static void backtrack(int r) {
        if(r == N) {
            ans++;
            return;
        }

        for(int c = 0; c < N; c++) {
            if(!visited1[c] && !visited2[r + c] && !visited3[N - r + c -1]) {
                visited1[c] = visited2[r + c] = visited3[N - r + c -1] = true;
                backtrack(r + 1);
                visited1[c] = visited2[r + c] = visited3[N - r + c -1] = false;
            }
        }
    }
}