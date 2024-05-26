package baekjoon.silver.silver3.boj1913_달팽이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;  // 홀수인 자연수 N
    static int findNum; // 위치를 찾고자 하는 N^2 이하의 자연수
    static int X, Y;
    static int[][] table; // 테이블
    static StringBuilder sb = new StringBuilder();
    /**
     * 상 우 하 좌
     */
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        findNum = Integer.parseInt(br.readLine());
        table = new int[N][N];
        makeTable(N);
        printTable();
    }

    private static void printTable() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(table[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(X).append(' ').append(Y);
        System.out.println(sb);
    }

    private static void makeTable(int N) {
        int x = (N - 1) / 2;
        int y = (N - 1) / 2;
        int direction = 0; // 방향
        int stepsTaken = 0; // 전진 횟수
        int turnCnt = 0; // 방향 전환 횟수
        int stepsLimit = 1; // 이동 거리
        for (int i = 1; i < (N * N) + 1; i++) {
            table[x][y] = i;

            if (i == findNum) { // 숫자 찾았다.
                X = x + 1;
                Y = y + 1;
            }

            // 다음 좌표
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 다음 좌표가 이동할 곳이 아니면 방향 전환
            if (nx >= N || nx < 0 || ny >= N || ny < 0 || table[nx][ny] != 0 || stepsTaken == stepsLimit) {
                direction = (direction + 1) % 4;
                nx = x + dx[direction];
                ny = y + dy[direction];
                turnCnt++; // 방향전환 횟수 증가
                stepsTaken = 0; // 전진 횟수 초기화

                if (turnCnt % 2 == 0) { // 방향전환 횟수가 2의 배수이면 거리 1 증가
                    stepsLimit++;
                }
            }
            stepsTaken++; // 전진 횟수 증가
            x = nx;
            y = ny;
        }
    }
}
