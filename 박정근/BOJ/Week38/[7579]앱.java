/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.12(Wed)
 * Runtime   : 136ms
 * Memory    : 18600KB
 * Algorithm : Dynamic Programming
 */

// >> 첫 번째 풀이 ( DP )
// Knapsack DP로 풀이했습니다. (Solve Time : 0h 19m) (136ms / 18600KB)

// 비용을 c만큼 사용해서 앱들을 비활성화 했을 때 얻을 수 있는 메모리의 최대 바이트를 구해, M 이상이 되면 C를 출력하는 코드입니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arrM = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arrM[i] = Integer.parseInt(st.nextToken());

        int[] arrC = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arrC[i] = Integer.parseInt(st.nextToken());

        // dp[i][j] : i번째 앱까지 활성화/비활성화 했을 때, 비용을 j만큼 사용했을 때의 얻을 수 있는 최대 메모리 바이트
        int[][] dp = new int[n+1][100 * n + 1];
        for (int i = 0; i < n+1; i++) Arrays.fill(dp[i], 0);


        boolean done = false;
        // Knapsack DP
        for (int weight = 0; weight <= 100 * n; weight++) {
            for (int thing = 1; thing <= n; thing++) {
                if (weight >= arrC[thing-1])
                    dp[thing][weight] = Math.max(dp[thing-1][weight], dp[thing-1][weight-arrC[thing-1]] + arrM[thing-1]);
                else
                    dp[thing][weight] = dp[thing-1][weight];

                if (dp[thing][weight] >= m) {
                    System.out.println(weight);
                    done = true;
                    break;
                }
            }
            if (done) break; 
        }
    }
}
