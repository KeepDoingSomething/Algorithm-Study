/**
 * Author    : Lee In Bok
 * Date      : 2024.11.29(Fri)
 * Runtime   : 480 ms
 * Memory    : 109732 KB
 * Algorithm : Two Pointer
 */

package com.year2024.Week26.G5_22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 짝수: 0 | 홀수: 1 로 먼저 계산
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).map(e -> e % 2).toArray();
        int N = info[0];
        int K = info[1];
        int l = 0;
        int r = 0;
        int ans = 0;     // 최장 짝수 수열 길이
        int remove = 0;  // 지운 개수 카운트

        while(r < N) {
            if(remove <= K) {  // 더 지울 수 있다면
                if(seq[r] == 1) {  // 홀수
                    remove++;  // 지우기
                }

                r++;  // 수열 길이 증가
            } else {  // 더 지울 수 없다면
                if(seq[l] == 1) {  // 홀수
                    remove--;  // 지웠던 수가 홀수 라면 지우기 가능 개수 증감
                }

                l++;  // 수열 길이 감소
            }

            // left 와 right 를 이용해 최장 수열을 계산 (지운 개수 수열의 길이에서 차감)
            ans = Math.max(ans, Math.abs(r - l) - remove);
        }

        System.out.println(ans);
    }
}