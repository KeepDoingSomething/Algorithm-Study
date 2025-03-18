/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.18(Tue)
 * Runtime   : 516ms
 * Memory    : 100020KB
 * Algorithm : DP
 */

// >> 첫 번째 풀이 ( LIS )
// DP LIS로 풀이했습니다. (Solve Time : 0h 9m)

// 가장 긴 증가하는 부분 수열을 DP를 활용해 O(nlogn) 으로 구했습니다.

import java.io.*;
import java.util.*;

class LIS {
    int n;
    int[] arr;
    int[] dp;
    public LIS (int n, int[] arr) {
        this.n = n;
        this.arr = new int[n];
        for (int i = 0; i < n; i++) this.arr[i] = arr[i];
        this.dp = new int[n];
    }

    public int lower_bound(int e, int k) {
        int start = 0;
        int end = e;
        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] < k) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    public int solve() {
        int answer = 0;
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            int idx = lower_bound(answer, arr[i]);
            if (idx == answer) answer += 1;
            dp[idx] = arr[i];
        }

        return answer;
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        LIS lis = new LIS(n, arr);
        System.out.println(lis.solve());
    }
}
