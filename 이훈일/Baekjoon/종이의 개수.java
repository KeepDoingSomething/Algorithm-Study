package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1780_2 {
    static int N;
    static int[][] graph;
    static int[] result = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0, 0, N);

        for(int i = 0; i < 3; i++) {
            System.out.println(result[i]);
        }
    }

    public static void partition(int x, int y, int size) {
        int first = graph[x][y];
        if(isSame(x, y, size)) {
            if(first == -1) {
                result[0]++;
            } else if(first == 0) {
                result[1]++;
            } else {
                result[2]++;
            }
            return;
        }

        int newSize = size / 3;

        partition(x, y, newSize);
        partition(x, y+newSize, newSize);
        partition(x, y+newSize*2, newSize);

        partition(x+newSize, y, newSize);
        partition(x+newSize, y+newSize, newSize);
        partition(x+newSize, y+newSize*2, newSize);

        partition(x+newSize*2, y, newSize);
        partition(x+newSize*2, y+newSize, newSize);
        partition(x+newSize*2, y+newSize*2, newSize);
    }

    public static boolean isSame(int x, int y, int size) {
        int first = graph[x][y];
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(graph[i][j] != first) {
                    return false;
                }
            }
        }
        return true;
    }
}
