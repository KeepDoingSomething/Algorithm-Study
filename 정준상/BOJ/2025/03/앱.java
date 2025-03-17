
import java.io.*;
import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-14
 * Runtime          :   156 ms
 * Memory           :   16.384 MB
 * Used algorithm   :   Dynamic programming
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[] bytes, costs;
    private static int N, M, MAX;

    private static void init() throws IOException {
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        bytes = new int[N];
        costs = new int[N];
        for (int i = 0; i < N; i++) {
            bytes[i] = Integer.parseInt(st1.nextToken());
            costs[i] = Integer.parseInt(st2.nextToken());
        }

        MAX = Arrays.stream(costs).sum();
    }

    public static void main(String[] args) throws IOException {
        init();

        int[] dp = new int[MAX + 1];

        for (int i = 0; i < N; i++) {
            int currByte = bytes[i];
            int currCost = costs[i];

            for (int cost = MAX; cost >= currCost; cost--) {
                int prev = dp[cost - currCost];
                dp[cost] = Math.max(dp[cost], prev + currByte);
            }
        }

        for (int minCost = 0; minCost <= MAX; minCost++) {
            if (dp[minCost] >= M) {
                System.out.println(minCost);
                System.exit(0);
            }
        }

        throw new RuntimeException();
    }
}
