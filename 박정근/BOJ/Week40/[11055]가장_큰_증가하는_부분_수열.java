/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.25(Tue)
 * Runtime   : 132ms
 * Memory    : 16220KB
 * Algorithm : Dynamic Programming
 */

// >> 첫 번째 풀이 ( Dynamic Programming )
// DP로 풀이했습니다. (Solve Time : 0h 13m)

// O(N^2) DP로 풀이했습니다. 
// 가장 큰 증가하는 부분 수열의 합을 구하면 되므로, DP 테이블에 "해당 인덱스를 포함했을 때의 최대 합"을 저장해서 풀이했습니다.

import java.util.*;
import java.io.*;
class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + arr[i] > dp[i]) // arr[j] < arr[i] (증가하고,) dp[j] + arr[i] > dp[i] (합한 값이 기존보다 더 크다면)
                    dp[i] = dp[j] + arr[i];
            }
        }
        
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
