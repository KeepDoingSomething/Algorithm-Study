/**
 * Author    : Kang San Ah
 * Date      : 2024.12.22(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : Topology sort
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 장난감조립 {
    static int n;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        int[] in = new int[n + 1];
        int[][] dag = new int[n + 1][n + 1];
        boolean[] isComb = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());

            dag[a][b] = Integer.parseInt(br.readLine());
            in[b]++;
            isComb[a] = true;
        }

        Queue q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                q.add(i);
                arr[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int node = (int) q.poll();

            for (int i = 1; i <= n; i++) {
                if (dag[node][i] != 0) {
                    arr[i] += arr[node] * dag[node][i];
                    in[i]--;

                    if (in[i] == 0)
                        q.add(i);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!isComb[i])
                System.out.print(i + " " + arr[i] + "\n");
        }

    }

}
