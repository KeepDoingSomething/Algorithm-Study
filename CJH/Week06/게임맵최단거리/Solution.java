import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int x;
    public int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    int[] dx = {0, 1, -1, 0};
    int[] dy = {1, 0, 0, -1};
    int n, m;
    boolean[][] visited;

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        return BFS(0, 0, maps);
    }

    private int BFS(int x, int y, int[][] maps) {
        Queue<Node> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Node(x, y));

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || n - 1 < nx  || ny < 0 || m - 1 < ny) continue;
                if (visited[nx][ny]) continue;
                if (maps[nx][ny] != 1) continue;

                visited[nx][ny] = true;
                maps[nx][ny] = maps[node.x][node.y] + 1;
                q.offer(new Node(nx, ny));
            }
        }

        return maps[n - 1][m - 1] == 1 ? -1 : maps[n - 1][m - 1];
    }
}
