/**
 * Author    : Lee In Bok
 * Date      : 2024.10.19(Sat)
 * Runtime   : 948 ms
 * Memory    : 299024 KB
 * Algorithm : Graph Search
 */

package com.year2024.Week21.G3_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G3_9466 {

    public static int N;
    public static int cycleCnt;
    public static boolean[] visited;
    public static boolean[] cycled;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            cycleCnt = 0;
            visited = new boolean[N];
            cycled = new boolean[N];
            int[] board = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .map(e -> e - 1)
                                .toArray();

            for(int i = 0; i < N; i++) {
                if(!cycled[i]) {
                    dfs(board, i);
                }
            }

            sb.append((N - cycleCnt))
              .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    public static void dfs(int[] board, int num) {
        if(cycled[num]) return;

        if(visited[num]) {
            cycled[num] = true;
            cycleCnt++;
        }

        visited[num] = true;
        dfs(board, board[num]);
        cycled[num] = true;
        visited[num] = false;
    }
}