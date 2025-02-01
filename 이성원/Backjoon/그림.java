import java.io.*;
import java.util.*;

public class Main {

    static int map[][];
    static boolean visit[][];
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    static int N;
    static int M;

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map= new int[N][M];
        visit = new boolean[N][M];


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int paint = 0;

        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visit[i][j] && map[i][j] == 1) {
                    int result = dfs(i,j);

                    max = Math.max(max, result);
                    paint++;
                }
            }
        }

        if(paint == 0) {
            max = 0;
        }
        System.out.println(paint);
        System.out.println(max);

    }

    public static int dfs(int x, int y) {
        visit[x][y] = true;

        int value = 1;

        for(int i =0; i < 4; i++) {
            int new_x = x + dx[i];
            int new_y = y + dy[i];

            if(new_x >=0 && new_x < N && new_y >=0 && new_y < M) {
                if(!visit[new_x][new_y] && map[new_x][new_y] == 1) {
                    value += dfs(new_x, new_y);
                }
            }
        }

        return value;
    }

}