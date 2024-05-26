/**
 * Author    : Heo jun boem
 * Date      : 2024.05.24(Fri)
 * Runtime   : 87300 KB
 * Memory    : 684 ms
 * Algorithm : Implementation
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[][] snail;

    // 진행방향 (아래, 우측, 위, 좌측)
    static int[] dirA = {1, 0, -1, 0};
    static int[] dirB = {0, 1, 0, -1};

    static int dir = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        snail = new int[n][n];
        // 달팽이 만듬
        makeSnail();

        // 출력 시작
        StringBuilder sb = new StringBuilder();
        int targetA = 0, targetB = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(snail[i][j] + " ");
                if (snail[i][j] == target) {
                    targetA = i+1;
                    targetB = j+1;
                }
            }
            sb.append("\n");
        }
        sb.append(targetA + " " + targetB);
        System.out.println(sb);
    }

    // 달팽이 만드는 함수
    static void makeSnail() {
        int a = 0, b = 0;

        for (int i = n*n; i > 0; i--) {
            snail[a][b] = i;

            int chkA = a + dirA[dir];
            int chkB = b + dirB[dir];
            dir = dirChk(chkA, chkB);

            a += dirA[dir];
            b += dirB[dir];
        }
    }

    // 검증 함수
    static int dirChk(int da, int db) {
        // 인덱스 에러 날시 방향 변경
        if ( da >= n || da < 0 || db >= n || db < 0)
            return (dir + 1) % 4;
        // 진행방향에 값이 존재하면 방향 변경
        else if ( snail[da][db] != 0)
            return (dir + 1) % 4;

        return dir;
    }
}
