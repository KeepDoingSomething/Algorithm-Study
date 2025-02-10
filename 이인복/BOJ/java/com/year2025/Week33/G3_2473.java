/**
 * Author    : Lee In Bok
 * Date      : 2025.02.08(Sat)
 * Runtime   : 204 ms
 * Memory    : 17216 KB
 * Algorithm : Two Pointer(?)
 */

package com.year2025.Week33;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G3_2473 {

    public static long[] ans = new long[3];
    public static long[] liquid;
    public static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        liquid = Arrays.stream(br.readLine().split(" "))
                       .mapToLong(Long::parseLong)
                       .sorted()
                       .toArray();
        int end = liquid.length - 1;

        for(int i = 0; i < liquid.length - 2; i++) {
            findMin(i, end);
        }

        Arrays.stream(ans).forEach(e -> System.out.print(e + " "));
    }

    /**
     * 고정 좌표: srt
     * 가변 좌표: mid, right
     * mid 와 right 를 증가, 감소 시키며 가장 0 과 가까운 값을 도출
     */
    public static void findMin(int srt, int end) {
        int mid = srt + 1;

        while(mid < end) {
            long sum = liquid[srt] + liquid[mid] + liquid[end];
            long absSum = Math.abs(sum);

            if(absSum < min) {
                min = absSum;
                ans[0] = liquid[srt];
                ans[1] = liquid[mid];
                ans[2] = liquid[end];
            }

            if(sum > 0) {
                end--;
            } else {
                mid++;
            }
        }
    }
}