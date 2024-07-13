import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_6593 {
    static int L, R, C;

    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 0, 1, 0, -1};
    static int[] dz = {0, 0, -1, 0, 1, 0};
    static int[][][] visit;

    static class Node{
        int x, y, z;

        Node(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void BFS(int x, int y, int z, String[][][] building){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, z));

        building[x][y][z] = "#";

        while (!q.isEmpty()){
            Node tmp = q.poll();

            for (int i = 0; i < 6; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                int nz = tmp.z + dz[i];

                if (nx >= 0 && ny >= 0 && nz >= 0 && nx < L && ny < R && nz < C && !building[nx][ny][nz].equals("#")){
                    visit[nx][ny][nz] = visit[tmp.x][tmp.y][tmp.z] + 1;
                    building[nx][ny][nz] = "#";

                    q.offer(new Node(nx, ny, nz));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[][][] building = new String[L][R][C];

        visit = new int[L][R][C];

        Node start = new Node(0, 0, 0);
        Node end = new Node(0, 0, 0);

        while (L != 0 && R != 0 && C != 0){
            for (int i = 0; i < L; i++) {

                for (int j = 0; j < R; j++) {
                    String str = br.readLine();

                    for (int k = 0; k < C; k++) {
                        if (str.charAt(k) == 'S'){
                            start = new Node(i, j, k);
                        }else if (str.charAt(k) == 'E'){
                            end = new Node(i, j, k);
                        }

                        building[i][j][k] = str.substring(k, k + 1);
                    }
                }
                String enter = br.readLine();

            }
            BFS(start.x, start.y, start.z, building);

            if (visit[end.x][end.y][end.z] != 0){
                System.out.println("Escaped in " + visit[end.x][end.y][end.z] + " minute(s).");
            }else {
                System.out.println("Trapped!");
            }

            st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            building = new String[L][R][C];
            visit = new int[L][R][C];
        }
    }
}
