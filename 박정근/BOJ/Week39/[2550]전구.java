/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.18(Tue)
 * Runtime   : 300ms
 * Memory    : 20900KB
 * Algorithm : DP
 */

// >> 첫 번째 풀이 ( LIS )
// DP LIS로 풀이했습니다. (Solve Time : 0h 51m)

// 가장 긴 증가하는 부분 수열 2 문제에서 역추적을 추가합니다.
// 자꾸 틀리길래, 유심히 생각해보니 역추적을 잘못 구현하고 있었습니다... 

import java.util.*;
import java.io.*;

class Bulb {
    int n;
    int[] switchs;
    int[] bulbsIdx;
    int[] dp;
    int[] backtrack;

    public Bulb (int n, int[] switchs, int[] bulbs) {
        this.n = n;
        this.switchs = new int[n];
        for (int i = 0; i < n; i++) this.switchs[i] = switchs[i];

        this.bulbsIdx = new int[n+1];
        for (int i = 0; i < n; i++) this.bulbsIdx[bulbs[i]] = i;

        this.dp = new int[n];
        this.backtrack = new int[n];
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
    public void solve() {
        int answer = 0;
        int now_end = 1;
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(backtrack, -1);

        for (int i = 0; i < n; i++) {
            int idx = lower_bound(answer, bulbsIdx[switchs[i]]);
            if (idx == answer) answer += 1;
            dp[idx] = bulbsIdx[switchs[i]];
            backtrack[i] = idx;
        }

        System.out.println(answer);

        int now_idx = answer - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (now_idx == backtrack[i]) {
                dp[now_idx] = switchs[i];
                now_idx -= 1;
            }

            if (now_idx < 0) break;
        }

        Arrays.sort(dp);

        for (int i = 0; i < answer; i++) System.out.print(dp[i] + " ");
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] switchs = new int[n];
        for (int i = 0; i < n; i++) switchs[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] bulbs = new int[n];
        for (int i = 0; i < n; i++) bulbs[i] = Integer.parseInt(st.nextToken());

        Bulb b = new Bulb(n, switchs, bulbs);
        b.solve();
    }
}
