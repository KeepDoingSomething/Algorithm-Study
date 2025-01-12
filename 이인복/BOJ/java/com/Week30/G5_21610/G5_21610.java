/**
 * Author    : Lee In Bok
 * Date      : 2025.01.11(Sat)
 * Runtime   : 552 ms
 * Memory    : 24672 KB
 * Algorithm : Implementation
 */

package com.Week30.G5_21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class G5_21610 {

    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Point> clouds = List.of(
                new Point(N - 1, 0),
                new Point(N - 1, 1),
                new Point(N - 2, 0),
                new Point(N - 2, 1)
        );

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());

            // 구름 이동
            clouds.forEach(e -> e.move(N, board, direction, dist));
            // 대각선 체크
            clouds.forEach(e -> e.checkDiagonal(N, board));
            // 새로운 구름 생성
            clouds = getNewClouds(board, clouds);
        }

        // 배열 모든 요소의 합 출력
        System.out.println(Arrays.stream(board)
                                 .flatMapToInt(IntStream::of)
                                 .sum());
    }

    public static List<Point> getNewClouds(int[][] board, List<Point> oldClouds) {
        List<Point> newClouds = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] > 1) {
                    Point point = new Point(i, j);

                    if(!oldClouds.contains(point)) {  // 기존에 구름이 생성되었던 위치는 배제
                        board[i][j] -= 2;  // 구름 생성 하면서 바구니에 물 2 감소
                        newClouds.add(point);
                    }
                }
            }
        }

        return newClouds;
    }

    static class Point {
        public static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
        public static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        public static final int[] diagonals = {1, 3, 5, 7};

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 이동 후 마지막 자리에서 바구니에 물 증가
        public Point move(int boundary, int[][] board, int direction, int dist) {
            for(int i = 0; i < dist; i++) {
                x = calcPoint(x, dx[direction], boundary);
                y = calcPoint(y, dy[direction], boundary);
            }

            board[x][y]++;

            return this;
        }

        // 대각선에 물이 있는 경우 현 위치 물 증가
        public Point checkDiagonal(int boundary, int[][] board) {
            for(int diagonal : diagonals) {
                int checkX = x + dx[diagonal];
                int checkY = y + dy[diagonal];

                if(isValidDiagonalPoint(checkX, checkY, boundary) && board[checkX][checkY] > 0) {
                    board[x][y]++;
                }
            }

            return this;
        }

        // 이동시 양 외각 사이드와 위와 아래가 이어져 있기 때문에 계산 해주는 함수
        public int calcPoint(int orgPoint, int movePoint, int boundary) {
            orgPoint = (orgPoint + movePoint) % boundary;

            return (orgPoint == -1) ? boundary - 1 : orgPoint;
        }

        // 대각선 체크할 때에는 양 끝이 이어져 있어도 체크하지 않기 때문에 대각선 유효 좌표 체크
        public boolean isValidDiagonalPoint(int x, int y, int boundary) {
            return 0 <= x && x < boundary && 0 <= y && y < boundary;
        }

        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;

            return x == point.x && y == point.y;
        }
    }
}