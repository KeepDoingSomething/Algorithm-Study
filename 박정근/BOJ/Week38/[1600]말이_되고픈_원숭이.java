/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.12(Wed)
 * Runtime   : 604ms
 * Memory    : 87504KB
 * Algorithm : BFS
 */

// >> 첫 번째 풀이 ( BFS )
// BFS로 풀이했습니다. (Solve Time : 0h 14m)

// BFS 로 탐색하여 (y, x) 까지 가는 최소 이동 횟수를 구하면서, 나이트로 몇 번 이동했는지의 횟수도 방문 처리 기준에 포함해야 합니다.
// 왜냐하면, 초반에 나이트 이동 횟수를 다 소모해 단순히 h x w 방문 처리를 하게 되었을 때, 목적지까지 가지 못하고 이동 횟수를 전부 소모한다면,
// 나중에 인접 이동으로 돌아와서 (나이트 이동 횟수를 소모하지 않은 상태) 나이트 이동을 했을 때 이미 방문 처리 된 곳이라 정답을 발견하지 못할 수 있습니다.
// 따라서, 나이트 이동 횟수마다 방문 처리를 다르게 함으로써, 나이트 이동을 어떻게-얼마나 사용했는지에 따라 다르게 경로를 찾을 수 있어야 합니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int i, j, c;
    public Node(int i, int j, int c) {
        this.i = i; this.j = j; this.c = c;
    }
}

class HorseMonkey {
    int k, w, h;
    int[][] maps;

    public HorseMonkey(int k, int w, int h, int[][] maps) {
        this.k = k; this.w = w; this.h = h;
        this.maps = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) this.maps[i][j] = maps[i][j];
        }
    }

    public int solve() {
        // visited[i][j][l] : (i, j) 칸을 나이트 이동방법으로 l번 이동했을 때 최소 횟수
        int[][][] visited = new int[h][w][k+1];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        // 0 ~ 3 : 인접 칸 이동, 4 ~ 11 : 나이트 이동
        int[] dy = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
        int[] dx = {0, 0, -1, 1, -2, -1, 1, 2, -2, -1, 1, 2};

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        visited[0][0][0] = 0;

        while(!q.isEmpty()) {
            Node now = q.poll();

            for (int d = 0; d < 12; d++) {
                int ny = now.i + dy[d];
                int nx = now.j + dx[d];

                if (0 <= ny && ny < h && 0 <= nx && nx < w && maps[ny][nx] == 0) {
                    // 인접 칸 이동
                    if ((d < 4) && 
                        (visited[ny][nx][now.c] == -1 || (visited[ny][nx][now.c] > visited[now.i][now.j][now.c] + 1))) {
                        visited[ny][nx][now.c] = visited[now.i][now.j][now.c] + 1;
                        q.add(new Node(ny, nx, now.c));
                    }
                    // 나이트 이동
                    else if ((d >= 4 && now.c + 1 <= k) &&
                        (visited[ny][nx][now.c + 1] == -1 || (visited[ny][nx][now.c + 1] > visited[now.i][now.j][now.c] + 1))) {
                        visited[ny][nx][now.c+1] = visited[now.i][now.j][now.c] + 1;
                        q.add(new Node(ny, nx, now.c + 1));
                    }
                }
            }
        }

        // 최소 행동 횟수 반환
        int ans = -1;
        for (int i = 0; i <= k; i++) {
            if (visited[h-1][w-1][i] != -1) {
                if (ans == -1 || (ans > visited[h-1][w-1][i])) ans = visited[h-1][w-1][i];
            }
        }
        return ans;
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][] maps = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++)
                maps[i][j] = Integer.parseInt(st.nextToken());
        }

        HorseMonkey hm = new HorseMonkey(k, w, h, maps);
        System.out.println(hm.solve());
    }
}
