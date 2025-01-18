/**
 * Author    : Lee In Bok
 * Date      : 2024.08.17(Sat)
 * Runtime   : 15304 KB
 * Memory    : 276 ms
 * Algorithm : Greedy
 */

package com.year2024.Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_1052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int buy = 0;

        while(true) {
            int cnt = 0;
            int copyN = N;

            while(copyN > 0) {
                cnt += copyN % 2;
                copyN /= 2;
            }

            if(cnt <= K) break;

            N++;
            buy++;
        }

        System.out.println(buy);
    }
}