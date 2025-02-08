package baekjoon.G2_1256;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] D = new int[202][202];

        for (int i = 0; i <= 200; i++) {  // 조합테이블 초기화
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    D[i][j] = 1;
                } else {
                    D[i][j] = D[i - 1][j - 1] + D[i - 1][j];
                    if (D[i][j] > 1000000000) D[i][j] = 1000000001; // K 범위 넘어갈경우
                }
            }
        }


        if (D[n + m][m] < k)
            System.out.println(-1);
        else {
            while (!(n == 0 && m == 0)) {
                // a 를 선택했을때 남은 문자로 만들수 있는 경우의 수가 k보다 크면
                if (D[n - 1 + m][m] >= k) { // 전체 경우의 수에서 a를 선택(-1)한 후 z의 문자 위치를 선택하는 경우(조합이라 볼수 있음)의 수
                    System.out.print("a");
                    n--;
                } else {
                    System.out.print("z");
                    k = k - D[n - 1 + m][m];
                    m--;
                }
            }
        }
    }
}