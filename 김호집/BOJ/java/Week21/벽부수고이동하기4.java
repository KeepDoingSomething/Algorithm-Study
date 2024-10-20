/**
 * Author    : HojipKim
 * Date      : 2024.10.19(Sat)
 * Runtime   : 956 ms
 * Memory    : 217048 KB
 * Algorithm : 너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색
 */

import java.io.*;
import java.util.*;

class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}

public class Main {

    static int n, m;
    static int[][] board;
    static int[][] answer;
    static int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static Map<Integer, Integer> map = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        answer = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = Character.getNumericValue(charArray[j]);
            }
        }

        int num = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    map.put(num, bfs(i, j, num));
                    num++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    answer[i][j] = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < directions.length; k++) {
                        int dx = directions[k][0];
                        int dy = directions[k][1];

                        int nx = i + dx;
                        int ny = j + dy;

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == 1) {
                            continue;
                        }
                        set.add(board[nx][ny]);
                    }

                    for (int a: set) {
                        answer[i][j] += map.get(a);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(String.valueOf(answer[i][j] % 10));
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static int bfs(int x, int y, int num) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        board[x][y] = num;
        int cnt = 1;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int dx = directions[i][0];
                int dy = directions[i][1];
                int nx = node.getX() + dx;
                int ny = node.getY() + dy;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] != 0) {
                    continue;
                }

                board[nx][ny] = num;
                queue.offer(new Node(nx, ny));
                cnt++;
            }
        }

        return cnt;
    }
}