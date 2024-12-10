/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.10(Tue)
 * Runtime   : 1028ms
 * Memory    : 14156KB
 * Algorithm : Recursion + Bitmask
 */

// >> 첫 번째 풀이 ( Recursion + Bitmask )
// 재귀와 비트마스킹으로 풀이했습니다. (Solve Time : 0h 11m)
// N-Queen 문제는 단순 재귀 구현으로 풀이해도 무방하지만, 비트 이론 (유사 흑마법)을 이용하면 1~2차원 배열을 사용하지 않아도 간단하게 구현할 수 있습니다.
// 주석에다 설명해보려 했지만 코드가 뚱뚱해질 것 같아서, 제가 예전에 정리해놓은 블로그 글을 보완해서 첨부하도록 하겠습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class NQueen {
    int n;
    long answer;
    public NQueen(int n) {
        this.n = n;
        this.answer = 0;
        solve(0, 0, 0, 0);
    }

    public long getAnswer() { return this.answer; }

    public void solve(int i, int h, int ld, int rd) {
        if (i == n) {
            answer += 1;
            return;
        }

        for (int idx = 0; idx < n; idx++) {
            // 같은 열, 왼쪽 대각선, 오른쪽 대각선에 퀸이 배치되지 않았다면 재귀 탐색
            if (((h | ld | rd) & (1 << idx)) == 0)
                solve(i + 1, (h | (1 << idx)), (((ld | (1 << idx)) << 1) % (1 << n)), ((rd | (1 << idx)) >> 1));
        }
    }
}

class Main {
	static public void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        NQueen nq = new NQueen(n); // N-Queen 클래스
        System.out.println(nq.getAnswer());
    }
}
