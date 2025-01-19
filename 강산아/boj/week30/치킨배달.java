/**
 * Author    : Kang San Ah
 * Date      : 2025.01.19(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : dfs
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 치킨배달 {
    static int N, M, ans;
    static int[][] map;
    static ArrayList<Point> person, chicken;
    static boolean[] combi;

    public static void DFS(int start, int cnt) {
        if (cnt == M) {
            int res = 0;

            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;

                for (int j = 0; j < chicken.size(); j++) {
                    if (combi[j]) {
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x)
                                + Math.abs(person.get(i).y - chicken.get(j).y);

                        temp = Math.min(temp, distance);
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            combi[i] = true;
            DFS(i + 1, cnt + 1);
            combi[i] = false;  // 백트래킹
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    person.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }
        combi = new boolean[chicken.size()];
        ans = Integer.MAX_VALUE;
        DFS(0, 0);
        System.out.println(ans);
    }
}
