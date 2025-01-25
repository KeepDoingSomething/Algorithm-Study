/**
 * Author    : Lee In Bok
 * Date      : 2025.01.25(Sat)
 * Runtime   : 1036 ms
 * Memory    : 319972 KB
 * Algorithm : Divide and Conquer
 */

package com.year2025.Week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2_1780 {

    public static int[][] board;
    public static Answer ans = new Answer();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();
        }

        divideAndConquer(0, 0, N);
        ans.printAnswer();
    }

    public static void divideAndConquer(int x, int y, int n) {
        int cnt = 0;

        // 범위 내의 종이 종류(-1, 0, 1) 카운팅 -> 시작이 1 이면 1의 개수만 카운팅
        for(int i = x; i < x + n; i++) {
            for(int j = y; j < y + n; j++) {
                if(board[x][y] != board[i][j]) {
                    break;
                }

                cnt++;
            }
        }

        // 모든 종이가 같은지 확인
        if(cnt == n * n) {
            ans.setAns(board[x][y]);
            return;
        }

        int dn = n / 3;

        // 시작 좌표 계산 후 재귀 호출
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                divideAndConquer(x + dn * i, y + dn * j, dn);
            }
        }
    }

    static class Answer {
        int neg;
        int zero;
        int pos;

        void printAnswer() {
            System.out.println(neg);
            System.out.println(zero);
            System.out.println(pos);
        }

        public void setAns(int num) {
            if(num == 1) {
                this.pos++;
            } else if(num == -1) {
                this.neg++;
            } else {
                this.zero++;
            }
        }
    }
}
