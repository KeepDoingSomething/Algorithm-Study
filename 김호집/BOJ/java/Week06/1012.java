/**
 * Author    : HojipKim
 * Date      : 2024.06.18(Tue)
 * Runtime   : 104 ms
 * Memory    : 14432 KB
 * Algorithm : 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
 */
class Node{
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

class Main{
    public static int T;
    private static int M, N, K;
    private static int[][] arr;
    private static boolean[][] isVisited;
    private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1 , 0}};

    private static Deque<Node> deq = new ArrayDeque<>();

    private static ArrayList<Integer> ansList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int answer = 0;
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N][M];
            isVisited = new boolean[N][M];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[y][x] = 1;
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    Node node = new Node(k, j);
                    answer += BFS(node);
                }

            }
            ansList.add(answer);
        }

        for (int i = 0; i < ansList.size(); i++) {
            bw.write(ansList.get(i) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(Node node) {
        int x = node.getX();
        int y = node.getY();

        if(isVisited[y][x] == true || arr[y][x] != 1){
            return 0;
        }

        deq.addLast(node);

        while(!deq.isEmpty()) {
            x = deq.peek().getX();
            y = deq.peek().getY();
            deq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = x+directions[i][1];
                int ny = y+directions[i][0];

                if(((0 <= nx) && (nx < M)) && ((0 <= ny) && (ny < N))){
                    if(isVisited[ny][nx] == false && arr[ny][nx] == 1) {
                        Node addNode = new Node(nx, ny);
                        deq.addLast(addNode);
                        isVisited[ny][nx] = true;
                    }
                }
            }
        }
        return 1;
    }
}