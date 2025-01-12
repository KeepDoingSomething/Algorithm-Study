package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon21610 {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static Queue<int[]> before = new LinkedList<>();
    static Queue<int[]> cloud = new LinkedList<>();
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = N-2; i < N; i++) {
            for(int j = 0; j < 2; j++) {
                before.add(new int[]{i, j});
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int dis = Integer.parseInt(st.nextToken());

            visited = new boolean[N][N];

            move(dir, dis);
            countDiagonal();
            addBefore();
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                answer += graph[i][j];
            }
        }

        System.out.println(answer);
    }

    public static void move(int dir, int dis) {
        while (!before.isEmpty()) {
            int[] node = before.poll();
            int nx = calculateIndex(node[0] + dx[dir] * dis);
            int ny = calculateIndex(node[1] + dy[dir] * dis);
            graph[nx][ny]++;
            visited[nx][ny] = true;
            cloud.add(new int[]{nx, ny});
        }
    }

    public static int calculateIndex(int num) {
        int mod = num % N;
        if(mod < 0) {
            mod += N;
        }
        return mod;
    }

    public static void countDiagonal() {
        while(!cloud.isEmpty()) {
            int[] node = cloud.poll();
            int x = node[0];
            int y = node[1];

            int count = 0;
            for(int i = 1; i < 8; i += 2) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if(graph[nx][ny] > 0) {
                        count++;
                    }
                }
            }
            graph[x][y] += count;
        }
    }

    public static void addBefore() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(graph[i][j] >= 2 && !visited[i][j]) {
                    graph[i][j] -= 2;
                    before.add(new int[]{i, j});
                }
            }
        }
    }
}
