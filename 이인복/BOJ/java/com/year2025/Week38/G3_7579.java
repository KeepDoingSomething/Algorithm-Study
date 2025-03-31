/**
 * Author    : Lee In Bok
 * Date      : 2025.03.19(Wed)
 * Runtime   : 156 ms
 * Memory    : 18960 KB
 * Algorithm : Dynamic Programming
 */

package com.year2025.Week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G3_7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] dp = new int[N][10001];
        int ans = Integer.MAX_VALUE;

        /**
         * i: 실행 중인 앱(입력)
         * j: 비용
         * value: j 에 해당 하는 비용 에서 최대 확보 가능한 최대 메모리
         */
        for(int i = 0; i < N; i++) {
            int m = memory[i];
            int t = times[i];

            for(int j = 0; j <= 10000; j++) {
                // j >= t: 소모 되는 비용이 현재 검색 중인 비용 보다 작다(ex - 3초 인데 4초 이상 배열에 초기화 할 수 없음)
                if(i == 0) {
                    if(j >= t) {
                        dp[i][j] = m;
                    }
                } else {
                    /**
                     * dp[i - 1]: 이전 앱
                     * [i - 1][j - t]: 이전 앱에서 현재, 소모 시간에 확보 메모리를 더해서 더 확보할 수 있는 최대 값을 계산
                     */
                    dp[i][j] = j >= t
                            ? Math.max(dp[i - 1][j], dp[i - 1][j - t] + m)
                            : dp[i - 1][j];
                }

                if(dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);
    }
}
