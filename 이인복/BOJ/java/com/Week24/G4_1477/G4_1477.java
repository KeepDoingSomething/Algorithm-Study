/**
 * Author    : Lee In Bok
 * Date      : 2024.11.11(Mon)
 * Runtime   : 104 ms
 * Memory    : 14284 KB
 * Algorithm : Binary Search
 */

package com.Week24.G4_1477;

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
        locations[0] = 0;

        for(int i = 1; i <= N; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }

        locations[N + 1] = L;
        Arrays.sort(locations);
        // Input End
        binarySearch(locations, L, M);
    }

    public static void binarySearch(int[] locations, int lastLoc, int target) {
        int left = 1;
        int right = lastLoc;

        while(left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;

            for(int i = 1; i < locations.length; i++) {
                cnt += (locations[i] - locations[i - 1] - 1) / mid;
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
/*
- left 0 으로 설정해서 삽질
- N 이 0 입력 대수롭지 않게 생각했는데 큰일
 */