
import java.io.*;
import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-14
 * Runtime          :   456 ms
 * Memory           :   89.672 MB
 * Used algorithm   :   BFS
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C, K;
    private static int[][] table;
    private static boolean[][][] visit;

    private static final int[]
            drM = {0, 0, 1, -1},
            dcM = {1, -1, 0, 0},
            drH = {1, 2, 2, 1, -1, -2, -2, -1},
            dcH = {2, 1, -1, -2, -2, -1, 1, 2};

    private static boolean inRange(int r, int c) {
        return (r >= 0 && r < R && c >= 0 && c < C);
    }

    private static boolean moveable(int r, int c, int jumps) {
        return inRange(r, c) && table[r][c] == 0 && !visit[r][c][jumps];
    }

    private static void init() throws IOException {
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        table = new int[R][];
        visit = new boolean[R][C][K + 1];

        for (int i = 0; i < R; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(bfs());
    }


    private static int bfs() {

        Queue<State> q = new LinkedList<>();

        // 다음 위치 queue 에 붙여주기
        Appender appendNextToQ = (curr, dr, dc, jumps) -> {
            for (int i = 0; i < dr.length; i++) {
                int r = curr.r + dr[i];
                int c = curr.c + dc[i];

                if (moveable(r, c, jumps)) {
                    visit[r][c][jumps] = true;
                    q.add(new State(r, c, curr.steps + 1, jumps));
                }
            }
        };

        return bfs(q, appendNextToQ);
    }

    private static int bfs(Queue<State> q, Appender appender)  {
        visit[0][0][K] = true;
        q.add(new State(0, 0, 0, K));

        while (!q.isEmpty()) {

            State curr = q.poll();
            int jumps = curr.remainJumps;

            if (curr.r == R - 1 && curr.c == C - 1) {
                return curr.steps;
            }

            // 원숭이 진행
            appender.doTask(curr, drM, dcM, jumps);

            // 말 진행
            if (jumps > 0)  {
                appender.doTask(curr, drH, dcH, jumps - 1);
            }
        }

        return -1;
    }
}

class State {

    int r, c;
    int steps, remainJumps;

    public State(int r, int c, int steps, int remainJumps) {
        this.r = r;
        this.c = c;
        this.steps = steps;
        this.remainJumps = remainJumps;
    }
}

@FunctionalInterface
interface Appender {
    void doTask(State curr, int[] dr, int[] dc, int jumps);
}
