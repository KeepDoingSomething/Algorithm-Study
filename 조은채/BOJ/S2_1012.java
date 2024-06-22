import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_1012 {
    static int M, N, K;
    static int[][] arr;

    static int answer;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void DFS(int j, int k){
        for (int i = 0; i < 4; i++) {
            int nx = j + dx[i];
            int ny = k + dy[i];

            if (nx < N && ny < M && nx >= 0 && ny >= 0 && arr[nx][ny] == 1){
                arr[nx][ny] = 0;

                DFS(nx, ny);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N][M];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());

                arr[n][m] = 1;
            }

            answer = 0;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[j][k] == 1){
                        answer++;
                        arr[j][k] = 0;

                        DFS(j, k);
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
