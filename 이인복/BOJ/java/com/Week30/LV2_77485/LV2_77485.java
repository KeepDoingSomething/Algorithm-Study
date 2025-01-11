/**
 * Author    : Lee In Bok
 * Date      : 2025.01.11(Sat)
 * Runtime   : 26.60 ms
 * Memory    : 113 MB
 * Algorithm : Array
 */

package com.Week30.LV2_77485;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class LV2_77485 {

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static final int RIGHT = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int UP = 3;


    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                Arrays.toString(sol.solution(6, 8, new int[][] {{1, 1, 1, 2}}))
        );
    }

    static class Solution {
        public int[] solution(int rows, int columns, int[][] queries) {
            List<Integer> ans = new ArrayList<>();
            int[][] board = getBoard(rows, columns);

            for(int[] query : queries) {
                int x1 = query[0] - 1;
                int y1 = query[1] - 1;
                int x2 = query[2] - 1;
                int y2 = query[3] - 1;

                ans.add(rotate(board, x1, y1, x2, y2));
            }

            return ans.stream()
                      .mapToInt(Integer::intValue)
                      .toArray();
        }

        public int rotate(int[][] board, int x1, int y1, int x2, int y2) {
            int x = x1;
            int y = y1;
            int min = Integer.MAX_VALUE;
            int prevNum = board[x + 1][y];

            for(int direction = 0; direction < 4; direction++) {
                while((direction == RIGHT && y != y2)
                   || (direction == DOWN && x != x2)
                   || (direction == LEFT && y != y1)
                   || (direction == UP && x != x1)
                ) {
                    int tmp = prevNum;

                    min = Math.min(min, prevNum);
                    tmp = prevNum;
                    prevNum = board[x][y];
                    board[x][y] = tmp;

                    x += dx[direction];
                    y += dy[direction];
                }
            }

            return min;
        }

        public int[][] getBoard(int rows, int columns) {
            return IntStream.range(0, rows)
                            .mapToObj(row -> IntStream.range(1, columns + 1)
                                                      .map(col -> row * columns + col)
                                                      .toArray()
                            )
                            .toArray(int[][]::new);
        }
    }
}
