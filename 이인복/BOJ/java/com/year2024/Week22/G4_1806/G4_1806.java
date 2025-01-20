/**
 * Author    : Lee In Bok
 * Date      : 2024.10.21(Mon)
 * Runtime   : 264 ms
 * Memory    : 25640 KB
 * Algorithm : Two Pointer
 */

package com.year2024.Week22.G4_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int l = 0;
        int r = 0;
        int sum = seq[0];
        int ans = Integer.MAX_VALUE;

        while(l < seq.length) {
            if(r == seq.length - 1 && sum < S) {
                break;
            }

            if(sum >= S) {
                ans = Math.min(ans, (r - l + 1));
                sum -= seq[l++];
            } else {
                sum += seq[++r];
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }
}