/**
 * Author    : Kang San Ah
 * Date      : 2024.11.08(Fri)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : DP
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리2 {

    static final int INF = 1000*1000 +1;

    static int N;
    static int[][] rgb, dp;
    static int answer = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        rgb = new int[N+1][3];
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < 3 ; j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+1][3];
        for(int k = 0 ; k < 3 ; k++) {

            for(int i = 0 ; i < 3 ; i++) {
                dp[1][i] = (i == k) ? rgb[1][i] : INF;
            }

            for(int i = 2 ; i <= N ; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + rgb[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + rgb[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + rgb[i][2];
            }


            for(int i = 0 ; i < 3 ; i++) {
                if(i != k)
                    answer = Math.min(answer, dp[N][i]);
            }
        }

        System.out.println(answer);
    }
}
