/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.19(Sun)
 * Runtime   : 212ms
 * Memory    : 17992KB
 * Algorithm : Brute Force + Backtracking
 */

// >> 첫 번째 풀이 ( 브루트포스 + 백트래킹 )
// 치킨집 M개를 고르는 백트래킹으로 풀이했습니다. (Solve Time : 1h 17m)
// 집과 치킨집의 개수가 많지 않으니, 치킨집 M개를 고르면 집과 가장 가까운 치킨집의 거리를 전부 더하며, 정답을 찾아갑니다.

// 다음 백트래킹 탐색을 진행하는 result = Math.min(result, solve(i + 1)); 문장에서,
// i + 1 이 아니라 start + 1 을 한 걸 못 봤었습니다.. 다른 걸 고쳐본답시고 시간을 날려먹었네요.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

// 2차원 좌표계 점 클래스
class Pos {
    int x, y;
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // 치킨 거리
    public int toCDist(Pos a) {
        return Math.abs(a.x - x) + Math.abs(a.y - y);
    }
}

class ChickenDelivery {
    int n, m;
    int[][] maps;
    ArrayList<Pos> chicken;
    ArrayList<Pos> home;
    int chicken_num;
    int[] selected;
    int selected_size;

    public ChickenDelivery(int n, int m, int[][] maps) {
        this.n = n;
        this.m = m;

        this.maps = new int[n][n];
        chicken = new ArrayList<Pos>(); // 치킨집 좌표 배열
        home = new ArrayList<Pos>(); // 집 좌표 배열
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.maps[i][j] = maps[i][j];

                if (maps[i][j] == 1) home.add(new Pos(i, j));
                else if (maps[i][j] == 2) chicken.add(new Pos(i, j));
            }
        }

        chicken_num = chicken.size();

        selected = new int[m];
        selected_size = 0;
    }

    public int solve(int start) {
        // 치킨집을 m개 골랐다면
        if (selected_size == m) {
            int now_res = 0;
            for (Pos h : home) {
                // 각 집과 가장 가까운 치킨집의 거리 구하기
                int now_min = Integer.MAX_VALUE;
                for (int s : selected) now_min = Math.min(now_min, h.toCDist(chicken.get(s)));
                now_res += now_min;
            }
            return now_res;
        }

        // Backtracking
        int result = Integer.MAX_VALUE;
        for (int i = start; i < chicken_num; i++) {
            selected[selected_size++] = i;
            // 중복 조합이 생기지 않도록, 다음 백트래킹의 탐색 시작 인덱스는 i + 1 부터
            result = Math.min(result, solve(i + 1));
            selected_size--;
        }
        return result;
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] maps = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) maps[i][j] = Integer.parseInt(st.nextToken());
        }

        ChickenDelivery cd = new ChickenDelivery(n, m, maps);
        System.out.println(cd.solve(0));
    }
}
