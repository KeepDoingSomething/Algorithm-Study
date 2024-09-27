package programmers.level2.리코쳇로봇;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int sRow, sCol, gRow, gCol;
    int[][] record;

    public int solution(String[] board) {
        record = new int[board.length][board[0].length()];

        for (int[] row : record) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    sRow = i;
                    sCol = j;
                }

                if (board[i].charAt(j) == 'G') {
                    gRow = i;
                    gCol = j;
                }
            }
        }

        bfs(board);

        return record[gRow][gCol];
    }

    private void bfs(String[] board) {
        int move = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sRow, sCol});
        record[sRow][sCol] = move;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int currentMoves = record[point[0]][point[1]];

            for (int i = 0; i < 4; i++) {
                // 갱신 작업을 위해 좌표 변수 할당
                int nx = point[0];
                int ny = point[1];

                while (true) {
                    // 다음 이동 임시 좌표를 구해서
                    int nextX = nx + dx[i];
                    int nextY = ny + dy[i];

                    if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length() || board[nextX].charAt(nextY) == 'D') {
                        break; // 벽이나 장애물에 부딪히면 멈춤
                    }

                    // 이상 없으면 이동
                    nx = nextX;
                    ny = nextY;
                }

                // 새로운 위치에 처음 도달한 경우에만 이동 기록
                if (record[nx][ny] == -1) {
                    record[nx][ny] = currentMoves + 1;
                    queue.add(new int[]{nx, ny});
                }

                // 목표 지점에 도달했을 경우 종료
                if (nx == gRow && ny == gCol) {
                    return;
                }

            }
        }
    }
}
