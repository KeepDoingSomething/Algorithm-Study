/**
 * Author    : Lee In Bok
 * Date      : 2025.03.23(Sun)
 * Runtime   : 500 ms
 * Memory    : 114564 KB
 * Algorithm : Binary Search
 */

package com.year2025.Week39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G2_12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] LIS = new int[A];
        int lastIdx = 1;

        LIS[0] = seq[0];


        for(int i = 1; i < A; i++) {
            int num = seq[i];

            if(LIS[lastIdx - 1] < num) {
                LIS[lastIdx] = num;
                lastIdx++;
            } else {
                int l = 0;
                int r = lastIdx;

                while(l < r) {
                    int mid = (l + r) / 2;

                    if(LIS[mid] < num) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }

                LIS[l] = num;
            }
        }

        System.out.println(lastIdx);
    }
}