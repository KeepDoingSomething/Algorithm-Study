/**
 * Author    : Lee In Bok
 * Date      : 2024.11.29(Fri)
 * Runtime   : 480 ms
 * Memory    : 109732 KB
 * Algorithm : Two Pointer
 */

package com.Week26.G5_22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(e -> e % 2).toArray();
        int N = info[0];
        int K = info[1];
        int l = 0;
        int r = 0;
        int ans = 0;
        int remove = 0;

        while(r < N) {
            if(remove <= K) {
                if(seq[r] == 1) {
                    remove++;
                }

                r++;
            } else {
                if(seq[l] == 1) {
                    remove--;
                }

                l++;
            }

            ans = Math.max(ans, Math.abs(r - l) - remove);
        }

        System.out.println(ans);
    }
}