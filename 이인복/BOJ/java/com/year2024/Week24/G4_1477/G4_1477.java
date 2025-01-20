/**
 * Author    : Lee In Bok
 * Date      : 2024.11.11(Mon)
 * Runtime   : 104 ms
 * Memory    : 14284 KB
 * Algorithm : Binary Search
 */

package com.year2024.Week24.G4_1477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 휴게소의 개수
        int M = Integer.parseInt(st.nextToken());  // 새로 짓는 휴게소의 개수
        int L = Integer.parseInt(st.nextToken());  // 고속도로의 길이
        int[] locations = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        locations[0] = 0;  // 시작

        for(int i = 1; i <= N; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }

        locations[N + 1] = L;  // 끝
        Arrays.sort(locations);
        // Input End
        binarySearch(locations, L, M);
    }

    public static void binarySearch(int[] locations, int lastLoc, int target) {
        int left = 1;
        int right = lastLoc;

        while(left <= right) {
            int mid = (left + right) / 2;  // 최소 값
            int cnt = 0;  // 최소 값으로 구간 사이의 휴게소 가능한 개수

            for(int i = 1; i < locations.length; i++) {
                cnt += (locations[i] - locations[i - 1] - 1) / mid;  // 0-100 사이의 거리는 99이기 때문에 -1 해준 값
            }

            if(cnt > target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }
}