/**
 * Author    : Lee In Bok
 * Date      : 2024.11.17(Sun)
 * Runtime   : 108 ms
 * Memory    : 14492 KB
 * Algorithm : Greedy, Implementation
 */

package com.Week25.G4_1461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 책의 개수
        int M = Integer.parseInt(st.nextToken());  // 한 번에 들 수 있는 수
        int[] arr = Arrays.stream(br.readLine().split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();
        Arrays.sort(arr);

        int l = 0;
        int r = arr.length - 1;
        // 가장 먼 거리를 마지막으로 가정했을 때 돌아오지 않아도 되니까 미리 감소
        int move = -1 * Math.max(Math.abs(arr[l]), Math.abs(arr[r]));

        while(l <= r) {
            int absL = Math.abs(arr[l]);
            int absR = Math.abs(arr[r]);

            // 이동일 때 음수와 양수가 섞인 케이스
            if(Math.abs(l - r) < M && arr[l] < 0 && arr[r] > 0) {
                move += (absL + absR) * 2;

                if(absL > absR) {
                    l += M;
                } else {
                    r -= M;
                }

                continue;
            }

            // 양수 또는 음수로만 이루어진 케이스
            if(absL > absR) {
                move += (absL * 2);
                l += M;
            } else {
                move += (absR * 2);
                r -= M;
            }
        }

        System.out.println(move);
    }
}