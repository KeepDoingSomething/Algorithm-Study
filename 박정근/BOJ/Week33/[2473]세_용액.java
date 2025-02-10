/**
 * Author    : Park Jeong Geun
 * Date      : 2025.02.04(Tue)
 * Runtime   : 1124ms
 * Memory    : 16888KB
 * Algorithm : Binary Search
 */

// >> 첫 번째 풀이 ( 이분 탐색 )
// 이분 탐색을 이용해 풀이했습니다. (Solve Time : 0h 32m)

// 용액 2개를 정해놓고, 0에 가깝게 만들 수 있는 남은 용액 하나를 이분 탐색으로 찾았습니다.

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.stream.Collectors;

class ThreeSolution {
    int n;
    long[] arr;
    long[] answer;
    long answer_sums;
    public ThreeSolution(int n, long[] arr) {
        this.n = n;
        this.arr = new long[n];
        for (int i = 0; i < n; i++) this.arr[i] = arr[i];
        Arrays.sort(this.arr); // 용액 정렬

        answer = new long[3];
        answer_sums = 0L;
        for (int i = 0; i < 3; i++) {
            answer[i] = this.arr[i];
            answer_sums += answer[i];
        }
        answer_sums = Math.abs(answer_sums);
    }

    int close_bound(int s, int e, long k) {
        // 이분 탐색
        while (s + 1 < e) {
            int m = (s + e) / 2;
            if (arr[m] < k) s = m;
            else e = m;
        }
        // 배열 원소 2개를 남겨놓고, k와 더 가까운 요소를 반환한다.
        if (Math.abs(k - arr[s]) < Math.abs(k - arr[e])) return s;
        return e;
    }

    void update_answer(int a, int b, int c) {
        // 더 0에 가깝다면, answer 배열에 업데이트하기
        if (Math.abs((long)(arr[a] + arr[b]) + arr[c]) < answer_sums) {
            answer[0] = arr[a]; answer[1] = arr[b]; answer[2] = arr[c];

            answer_sums = 0L;
            for (int i = 0; i < 3; i++) answer_sums += answer[i];
            answer_sums = Math.abs(answer_sums);
        }
    }

    public String solve() {
        // 1. a와 b 용액 정하기
        for (int a = 0; a < n - 1; a++) {
            for (int b = a + 1; b < n; b++) {
                long k = -(long)(arr[a] + arr[b]); // a와 b 용액을 정했을 때, 찾아야 할 값 k
                
                // 2. 0, a, b, n 사이를 close_bound 로 탐색하여 k와 가까운 용액이 있는지 찾기
                if (a > 0) // 0 ~ (a - 1)
                    update_answer(a, b, close_bound(0, a - 1, k));
                
                if (a + 1 < b) // (a + 1) ~ (b - 1)
                    update_answer(a, b, close_bound(a + 1, b - 1, k));
                
                if (b < n - 1) // (b + 1) ~ (n - 1)
                    update_answer(a, b, close_bound(b + 1, n - 1, k));
                
            }
        }

        Arrays.sort(answer);
        return Arrays.stream(answer)
               .mapToObj(String::valueOf)
               .collect(Collectors.joining(" "));
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Long.parseLong(st.nextToken());

        ThreeSolution ts = new ThreeSolution(n, arr);
        System.out.println(ts.solve());
    }
}
