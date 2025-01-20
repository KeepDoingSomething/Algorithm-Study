/**
 * Author    : Lee In Bok
 * Date      : 2024.10.05(Fri)
 * Runtime   : 128ms
 * Memory    : 15364KB
 * Algorithm : Binary Search
 */

package com.year2024.Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G4_13397 {

    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = 0;
        int right = Arrays.stream(arr).max().getAsInt();
        int ans = right;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(check(arr, mid)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean check(int[] arr, int mid) {
        int cnt = 1;
        int max = arr[0];
        int min = arr[0];

        for(int num : arr) {
            max = Math.max(max, num);
            min = Math.min(min, num);

            if(max - min > mid) {
                max = num;
                min = num;
                cnt++;
            }
        }

        return cnt <= M;
    }
}