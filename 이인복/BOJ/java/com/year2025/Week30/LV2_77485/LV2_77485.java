/**
 * Author    : Lee In Bok
 * Date      : 2025.01.11(Sat)
 * Runtime   : 26.60 ms
 * Memory    : 113 MB
 * Algorithm : Array
 */

package com.year2025.Week30.LV2_77485;

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
            int prevNum = board[x + 1][y];  // 초기 값은 현재 요소의 아래 위치에 있어서 세팅

            // 각 방향마다 경계선 까지 도착하면 종료 하도록 설정
            for(int direction = 0; direction < 4; direction++) {
                while((direction == RIGHT && y != y2)
                   || (direction == DOWN && x != x2)
                   || (direction == LEFT && y != y1)
                   || (direction == UP && x != x1)
                ) {
                    int tmp = prevNum;

                    min = Math.min(min, prevNum);  // 회전할 때 최소 값 찾기
                    prevNum = board[x][y];  // swap
                    board[x][y] = tmp;  // swap

                    x += dx[direction];
                    y += dy[direction];
                }
            }

            return min;
        }

        // 2 차원 배열 요소 1 부터 차례로 생성
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
