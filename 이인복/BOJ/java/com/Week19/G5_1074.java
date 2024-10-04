/**
 * Author    : Lee In Bok
 * Date      : 2024.10.05(Fri)
 * Runtime   : 100ms
 * Memory    : 14284KB
 * Algorithm : Divide And Conquer
 */

package com.Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_1074 {

    public static int N;
    public static int r;
    public static int c;
    public static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        search(0, 0);
    }

    // x, y 1 사분면의 시작 지점
    public static void search(int x, int y) {
        final int n = (int) Math.pow(2, N);  // 2^N
        final int t = n * 2;
        final int quadSize = n * n;  // 사분면에 속한 요소의 개수

        if(N-- >= 0) {
            if(x <= r && r < x + n && y <= c && c < y + n) {
                search(x, y);
            } else if(x <= r && r < x + n && y + n <= c && c < y + t) {
                ans += quadSize;
                search(x, y + n);
            } else if(x + n <= r && r < x + t && y <= c && c < y + n) {
                ans += (quadSize * 2);
                search(x + n, y);
            } else {
                ans += (quadSize * 3);
                search(x + n, y + n);
            }
            return;
        }

        System.out.println(ans);
    }
}