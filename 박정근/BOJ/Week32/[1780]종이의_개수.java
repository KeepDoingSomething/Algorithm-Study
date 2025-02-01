/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.20(Mon)
 * Runtime   : 1220ms
 * Memory    : 337736KB
 * Algorithm : Divide & Conquer
 */

// >> 첫 번째 풀이 ( 분할 정복 )
// 분할 정복 재귀로 풀이했습니다. (Solve Time : 0h 13m)
// 한 종이가 같은 숫자로 이루어지지 않는다면, 9개 영역을 분할 (Divide) 하여 탐색하면 됩니다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class DividePaper {
    int n;
    int[][] arr;
    int[] answer; // -1, 0, 1 로 채워진 종이의 개수를 각각 저장

    public DividePaper(int n, int[][] arr) {
        this.n = n;
        this.arr = new int[n][n];
        this.answer = new int[3]; 

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) this.arr[i][j] = arr[i][j];
        }
    }

    public void solve(int x1, int y1, int x2, int y2) {
        int[] now = {0, 0, 0};
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                // -1 을 0번 인덱스에, 0을 1번 인덱스에, 1을 2번 인덱스에 카운트
                now[arr[x][y] + 1] += 1;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (now[i] == (x2 - x1) * (y2 - y1)) {
                // 분할한 종이를 한 숫자로만 가득 채운다면, 해당 종이 1개 식별한 것임!
                answer[i] += 1;
                return;
            }
        }

        int block = (x2 - x1) / 3;

        // 왼쪽 위쪽, 가운데 위쪽, 오른쪽 위쪽
        // 왼쪽 중간, 가운데 중간, 오른쪽 중간
        // 왼쪽 아래, 가운데 아래, 오른쪽 아래
        // .. 를 분할하는 가중치 배열
        int[] dx1 = {0, 0, 0, 1, 1, 1, 2, 2, 2};
        int[] dy1 = {0, 1, 2, 0, 1, 2, 0, 1, 2};
        int[] dx2 = {1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] dy2 = {1, 2, 3, 1, 2, 3, 1, 2, 3};

        for (int d = 0; d < 9; d++) {
            int nx1 = x1 + (block * dx1[d]);
            int ny1 = y1 + (block * dy1[d]);
            int nx2 = x1 + (block * dx2[d]);
            int ny2 = y1 + (block * dy2[d]);

            solve(nx1, ny1, nx2, ny2);
        }

        /** 위 코드는 아래와 같습니다.

        // 왼쪽 위
        solve(x1, y1, x1 + block, y1 + block);
        // 가운데 위
        solve(x1, y1 + block, x1 + block, y2 - block);
        // 오른쪽 위
        solve(x1, y2 - block, x1 + block, y2);

        // 왼쪽 중간
        solve(x1 + block, y1, x2 - block, y1 + block);
        // 가운데 중간
        solve(x1 + block, y1 + block, x2 - block, y2 - block);
        // 오른쪽 중간
        solve(x1 + block, y2 - block, x2 - block, y2);

        // 왼쪽 아래
        solve(x2 - block, y1, x2, y1 + block);
        // 가운데 아래
        solve(x2 - block, y1 + block, x2, y2 - block);
        // 오른쪽 아래
        solve(x2 - block, y2 - block, x2, y2);

        **/
    }

    public void printAnswer() {
        for (int i = 0; i < 3; i++) System.out.println(answer[i]);
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());    
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DividePaper dp = new DividePaper(n, arr);
        dp.solve(0, 0, n, n);
        dp.printAnswer();
    }
}
