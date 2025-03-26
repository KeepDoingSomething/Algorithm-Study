import java.io.*;
import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-25
 * Runtime          :   124 ms
 * Memory           :   14.736 MB
 * Used algorithm   :   Dynamic programming
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] numbers, dp;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
    }

    public static void main(String[] args) throws IOException {

        init();
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int curr = numbers[i];
            dp[i] = curr;

            for (int j = 0; j < i; j++) {
                int prev = numbers[j];

                if (curr <= prev)    {
                    continue;
                }

                dp[i] = Math.max(dp[i], dp[j] + curr);
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
