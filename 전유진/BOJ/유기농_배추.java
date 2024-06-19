import java.util.Scanner;
/**
 * Author    : Jeon Yu Jin
 * Date      : 2024.06.19(Wed)
 * Runtime   : 29408 KB
 * Memory    : 408 ms
 * Algorithm : DFS
 */

public class 유기농_배추 {
    static int N;
    static int M;
    static int[][] arr;
    static int res = 0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            String[] split = sc.nextLine().split(" ");
            M = Integer.parseInt(split[0]);
            N = Integer.parseInt(split[1]);
            int K = Integer.parseInt(split[2]);
            arr = new int[M][N];

            for (int j = 0; j < K; j++) {
                String[] split1 = sc.nextLine().split(" ");
                int x = Integer.parseInt(split1[0]);
                int y = Integer.parseInt(split1[1]);
                arr[x][y] = 1;
            }

            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (arr[j][k] == 1) {
                        dfs(j, k);
                        res += 1;
                    }
                }
            }


            System.out.println(res);
            res = 0;

        }
    }

    private static void dfs(int x, int y) {
        if (x < 0 || x >= M || y < 0 || y >= N) return;

        if (arr[x][y] == 0) return;
        if (arr[x][y] == 1) {
            arr[x][y] = 0;
            dfs(x + 1, y);
            dfs(x, y + 1);
            dfs(x - 1, y);
            dfs(x, y - 1);
            return;
        }
    }
}
