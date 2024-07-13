import java.util.LinkedList;
import java.util.Queue;

public class Lv2_1844 {
    static int n, m;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] visit;

    class Node{
        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void BFS(int x, int y, int[][] maps){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));

        maps[x][y] = 0;

        while(!q.isEmpty()){
            Node tmp = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m && maps[nx][ny] == 1){
                    maps[nx][ny] = 0;

                    q.offer(new Node(nx, ny));

                    visit[nx][ny] = visit[tmp.x][tmp.y] + 1;
                }
            }
        }
    }

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;

        visit = new int[n][m];
        visit[0][0] = 1;
        visit[n - 1][m - 1] = -1;

        BFS(0, 0, maps);

        return visit[n - 1][m - 1];
    }

    public static void main(String[] args) {
        Lv2_1844 sol = new Lv2_1844();
        int[][] maps = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}};

        System.out.println(sol.solution(maps));
    }
}
