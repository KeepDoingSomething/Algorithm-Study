/**
 * Author    : HojipKim
 * Date      : 2024.06.27(Thu)
 * Runtime   : 168 ms
 * Memory    : 17960 KB
 * Algorithm : 너비 우선 탐색, 그래프 이론, 그래프 탐색
 */
class Node {
    private int x;
    private int y;
    private int z;

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}

class Main {

    public static int[][] directions = new int[][]{{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
    public static int[][][] visited;
    public static String[][][] building;

    static Deque<Node> deq = new ArrayDeque<>();

    public static int L = -1;
    public static int R = -1;
    public static int C = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            building = new String[L][R][C];
            visited = new int[L][R][C];

            Node node = null;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = String.valueOf(line.charAt(k));
                        if(building[i][j][k].equals("S")){
                            node = new Node(k, j, i);
                        }
                    }
                }
                br.readLine();
            }
            if(node != null) {
                BFS(node, bw);
            };
        }
        bw.flush();
    }

    public static void BFS(Node node, BufferedWriter bw) throws IOException {
        deq.clear();

        deq.offer(node);

        visited[node.getZ()][node.getY()][node.getX()] = 1;

        while(!deq.isEmpty()){
            Node cur = deq.poll();
            int dx = cur.getX();
            int dy = cur.getY();
            int dz = cur.getZ();

            for (int i = 0; i < 6; i++) {
                int nx = dx + directions[i][0];
                int ny = dy + directions[i][1];
                int nz = dz + directions[i][2];

                if (0 <= nx && nx < C && 0 <= ny && ny < R && 0 <= nz && nz < L){
                    if ((building[nz][ny][nx].equals(".") || building[nz][ny][nx].equals("E")) && visited[nz][ny][nx] == 0) {
                        visited[nz][ny][nx] = visited[dz][dy][dx] + 1;
                        deq.offer(new Node(nx, ny, nz));
                        if(building[nz][ny][nx].equals("E")) {
                            bw.write("Escaped in " + (visited[nz][ny][nx]-1) + " minute(s).\n");
                            return;
                        }
                    }
                }
            }
        }
        bw.write("Trapped!\n");
    }

}