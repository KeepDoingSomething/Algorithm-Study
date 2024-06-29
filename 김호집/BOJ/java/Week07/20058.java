/**
 * Author    : HojipKim
 * Date      : 2024.06.29(Sat)
 * Runtime   : 516 ms
 * Memory    : 156648 KB
 * Algorithm : 너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색, 구현, 시뮬레이션
 */
class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Main {
    static int N, Q, L, mapSize;
    static int[][] map;
    static int iceSum = 0;
    static int iceCnt = 0;
    static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        mapSize = (int) Math.pow(2, N);
        map = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L = Integer.parseInt(st.nextToken());
            iceCnt = 0;
            if (L > 0) {
                L = (int) Math.pow(2, L);
            }
            rotateGrids();
            meltIce();
            findIce();
        }

        bw.write(iceSum + "\n");
        bw.write(iceCnt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void rotateGrids() {
        for (int i = 0; i < mapSize; i += L) {
            for (int j = 0; j < mapSize; j += L) {
                // 각 LxL 블록을 회전
                int[][] temp = new int[L][L];
                for (int y = 0; y < L; y++) {
                    for (int x = 0; x < L; x++) {
                        temp[x][L - 1 - y] = map[i + y][j + x];
                    }
                }
                for (int y = 0; y < L; y++) {
                    for (int x = 0; x < L; x++) {
                        map[i + y][j + x] = temp[y][x];
                    }
                }
            }
        }
    }

    static void meltIce() {
        int[][] newMap = new int[mapSize][mapSize];
        int sum = 0;
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                int count = 0;
                for (int[] d : directions) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    if (nx >= 0 && nx < mapSize && ny >= 0 && ny < mapSize) {
                        if (map[ny][nx] > 0) {
                            count++;
                        }
                    }
                }
                newMap[y][x] = map[y][x];
                if (map[y][x] > 0 && count < 3) {
                    newMap[y][x]--;
                }
                sum += newMap[y][x];
            }
        }
        map = newMap;
        iceSum = sum;
    }

    static void findIce() {
        boolean[][] visited = new boolean[mapSize][mapSize];
        int maxIce = 0;

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if (!visited[y][x] && map[y][x] > 0) {
                    int size = bfs(y, x, visited);
                    maxIce = Math.max(maxIce, size);
                }
            }
        }
        iceCnt = maxIce;
    }

    static int bfs(int startY, int startX, boolean[][] visited) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(startX, startY));
        visited[startY][startX] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int[] dir : directions) {
                int nx = cur.getX() + dir[0];
                int ny = cur.getY() + dir[1];
                if (nx >= 0 && nx < mapSize && ny >= 0 && ny < mapSize && !visited[ny][nx] && map[ny][nx] > 0) {
                    queue.offer(new Node(nx, ny));
                    visited[ny][nx] = true;
                    count++;
                }
            }
        }

        return count;
    }
}
