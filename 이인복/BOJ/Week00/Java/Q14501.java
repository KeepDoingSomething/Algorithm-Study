/**
 * Author    : Lee In Bok
 * Date      : 2024.05.14(Tue)
 * Runtime   : 14200 KB
 * Memory    : 124 ms
 * Algorithm : Brute Force
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14501 {

    public static int N;
    public static int[] days;
    public static int[] costs;
    public static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        days = new int[N];
        costs = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        System.out.println(ans);
    }

    public static void dfs(int day, int cost) {
        if(day >= N) {
            ans = Math.max(cost, ans);
            return;
        }

        int nextDay = day + days[day];

        dfs(nextDay, cost + (nextDay <= N ? costs[day] : 0));  // 상담 가능 : 상담 불가능
        dfs(day + 1, cost);  // 상담 패스
    }
}