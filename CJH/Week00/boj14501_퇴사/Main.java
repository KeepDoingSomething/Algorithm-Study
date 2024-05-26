/**
* Author    : Choi Jiho
* Date      : 2024.05.16(Thu)
* Runtime   : 128 ms
* Memory    : 14216 KB
* Algorithm : Backtracking, DFS
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] T, P;
    static int ans;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            T[i] = a;
            P[i] = b;
        }

        dfs(0, 0);

        System.out.println(ans);
    }

    private static void dfs(int n, int revenue) {
        // 1. 종료조건: 가능한 n을 종료에 관련된 것으로 정의
        if (n >= N) {
            ans = Math.max(ans, revenue);
            return;
        }

        // 2. 하부 호출: 화살표 갯수만큼 호출
        if (n + T[n] <= N) { // 상담하는 경우 (퇴사 이전 완료 가능할 때만 상담)
            dfs(n + T[n], revenue + P[n]);
        }
        dfs(n + 1, revenue);// 상담하지 않는 경우 (항상 가능)
    }
}
