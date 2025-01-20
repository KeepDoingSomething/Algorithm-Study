/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.20(Mon)
 * Runtime   : 416ms
 * Memory    : 46652KB
 * Algorithm : BFS
 */

// >> 첫 번째 풀이 ( 너비 우선 탐색 )
// 깊이 우선 탐색 혹은 너비 우선 탐색을 배울 수 있는 문제입니다. (Solve Time : 0h 9m)
// 가로 세로로 이웃한 1 영역을 탐색하면서, 가장 큰 1 영역의 크기도 구했습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Node {
    int i, j;
    public Node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Paintings {
    int n, m;
    int[][] arr;
    int[] answer;
    public Paintings(int n, int m, int[][] arr) {
        this.n = n;
        this.m = m;
        this.arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) this.arr[i][j] = arr[i][j];
        }
        this.answer = new int[2]; // 그림의 개수, 가장 넓은 그림의 널비
        answer[0] = 0; answer[1] = 0;
    }

    public void solve() {
        int[][] vis = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(vis[i], 0);

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && vis[i][j] == 0) {
                    vis[i][j] = 1;
                    Queue<Node> q = new LinkedList<>();
                    q.add(new Node(i, j));

                    int cnt = 0;
                    while (!q.isEmpty()) {
                        Node now = q.poll();
                        cnt += 1;

                        for (int d = 0; d < 4; d++) {
                            int ny = now.i + dy[d];
                            int nx = now.j + dx[d];

                            if (0 <= ny && ny < n && 0 <= nx && nx < m && arr[ny][nx] == 1 && vis[ny][nx] == 0) {
                                vis[ny][nx] = 1;
                                q.add(new Node(ny, nx));
                            }
                        }
                    }
                    answer[0] += 1; // 그림의 개수 1 추가
                    answer[1] = Math.max(answer[1], cnt); // 최대 그림 넓이
                }
            }
        }
    }

    public void printAnswer() {
        for (int i = 0; i < 2; i++) System.out.println(answer[i]); 
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        Paintings p = new Paintings(n, m, arr);
        p.solve();
        p.printAnswer();
    }
}
