/**
 * Author    : Heo jun boem
 * Date      : 2024.05.17(Fri)
 * Runtime   : 14340 KB
 * Memory    : 128 ms
 * Algorithm : DFS
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] time;
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        time = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(result);
    }

    static void dfs(int idx, int pay) {
        if (idx >= n) {
            result = Math.max(result, pay);
            return;
        }

        if (idx + time[idx][0] <= n)
            dfs(idx + time[idx][0], pay + time[idx][1]);
        else
            dfs(idx + time[idx][0], pay);

        dfs(idx + 1, pay);
    }
}
