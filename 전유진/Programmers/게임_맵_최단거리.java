import java.util.LinkedList;
import java.util.Queue;
/**
 * Author    : Jeon Yu Jin
 * Date      : 2024.06.24(Mon)
 * Algorithm : BFSr
 * X
 */
public class 게임_맵_최단거리 {
    //0 : 벽
    public static void main(String[] args) {
        int[][] maps = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };
        System.out.println(solution(maps));
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static int solution(int[][] maps) {

        int answer = 0;

        int[][] visited = new int[maps.length][maps[0].length];

        bfs(maps, visited);
        answer = visited[maps.length - 1][maps[0].length - 1];

        if (answer == 0) {
            answer = -1;
        }

        return answer;

    }

    private static void bfs(int[][] maps, int[][] visited) {
        int x = 0;
        int y = 0;
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];

            for (int i = 0; i < 4; i++) {
                int nX = cX + dx[i];
                int nY = cY + dy[i];

                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1)
                    continue;

                if (visited[nX][nY] == 0 && maps[nX][nY] == 1) {
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queue.add(new int[]{nX, nY});
                }
            }

        }

    }
}
