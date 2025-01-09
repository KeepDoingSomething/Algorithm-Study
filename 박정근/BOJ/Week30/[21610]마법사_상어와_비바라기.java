/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.09(Thu)
 * Runtime   : 208ms
 * Memory    : 23868KB
 * Algorithm : Brute Force + Queue
 */

// >> 첫 번째 풀이 ( 완전 탐색 + 큐 )
// 완전 탐색 풀이에 큐를 녹여 구현했습니다. (Solve Time : 0h 35m)
// 지문대로 단순히 구현하는 문제입니다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

class Node {
    int i, j, adds;
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
        this.adds = 0; // 증가하는 물의 양
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] water = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(visited[i], false);

        // 0. 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2) 에 비구름이 생긴다.
        ArrayList<Node> clouds = new ArrayList<>();
        clouds.add(new Node(n-1, 0)); clouds.add(new Node(n-1, 1));
        clouds.add(new Node(n-2, 0)); clouds.add(new Node(n-2, 1));

        int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

        for (int move = 0; move < m; move++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            for (Node c : clouds) {
                // 1. 모든 구름이 d 방향으로 s 칸 이동한다.
                c.i += dy[d] * s;
                c.j += dx[d] * s; 
                
                // 구름의 이동 결과를 한 번에 구할 수 있는 모듈러 식입니다. n으로 나눈 나머지로 바꿉니다.
                if (c.i < 0) c.i = (c.i % n) + ((c.i % n == 0) ? 0 : n);
                if (c.j < 0) c.j = (c.j % n) + ((c.j % n == 0) ? 0 : n);
                
                c.i %= n;
                c.j %= n;

                // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
                water[c.i][c.j] += 1;

                // 3. 구름이 모두 사라진다. (리스트를 나중에 비울 에정)
                visited[c.i][c.j] = true;
            }

            for (Node c : clouds) {
                // 2번으로 인해 물이 도중에 증가하면 4번이 이상하게 처리될 수 있다.

                // 4. 물이 증가한 칸에 물복사버그 마법을 시전한다.
                for (int dir = 1; dir < 8; dir += 2) {
                    if (0 <= c.i + dy[dir] && c.i + dy[dir] < n && 0 <= c.j + dx[dir] && c.j + dx[dir] < n && water[c.i + dy[dir]][c.j + dx[dir]] > 0) {
                        c.adds += 1;
                    }
                }
            }

            for (Node c : clouds) {
                // 4번으로 인해 물이 도중에 증가하면 c.adds가 이상한 값이 될 수 있다.
                water[c.i][c.j] += c.adds;
            }

            clouds.clear();

            // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니여야 한다.
                    if (water[i][j] >= 2 && visited[i][j] == false) {
                        water[i][j] -= 2;
                        clouds.add(new Node(i, j));
                    }
                    visited[i][j] = false;
                }
            }
        }

        // 6. 바구니에 들어있는 물의 양의 합 출력
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) answer += water[i][j];
        }
        System.out.println(answer);
    }
}
