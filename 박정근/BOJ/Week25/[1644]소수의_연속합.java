/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.18(Mon)
 * Runtime   : 348 ms
 * Memory    : 32780 KB
 * Algorithm : Sieve + Two Pointer
 */

// >> 첫 번째 풀이 ( 에라토스테네스의 체 + 투 포인터 )
// 1. 에라토스테네스의 체로 최대 4백만까지의 소수 리스트를 구한 뒤 연속한 소수 배열을 만든다.
// 2. 투 포인터로 연속된 소수의 합을 찾기 위해 현재의 합을 증가시키거나 (right++) 감소시켜서 (left++) n을 나타낼 수 있는 경우의 수를 카운트한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
    public static int solve(int n) {
        // 1. 에라토스테네스의 체로 소수 구분하기
        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);
        arr[0] = 0; arr[1] = 0;
        for (int i = 2; i < n+1; i++) {
            for (int j = 2; i * j < n + 1; j++) arr[i * j] = 0;
        }

        // 2. 소수 개수 구하기
        int cnt = 0;
        for (int i = 2; i < n+1; i++) cnt += arr[i];

        if (cnt == 0) return 0;
        
        // 3. 소수 배열 만들기
        int[] parr = new int[cnt];
        int parrIdx = 0;
        for (int i = 2; i < n+1; i++) {
            if (arr[i] == 1) {
                parr[parrIdx] = i;
                parrIdx += 1;
            }
        }

        // 4. 투 포인터로 n 찾기
        int left = 0;
        int right = 0;
        int now = parr[0]; // left ~ right 까지의 연속된 소수의 합
        int ans = 0; // n으로 나타낼 수 있는 경우의 수
        while (right < cnt) {
            if (now > n) { // 현재 now 가 n보다 클 경우
                if (left == right) break; // 원소 1개인데도 n보다 크다면, 증가 수열이기에 이 이후에도 n을 찾을 수 없다.
                else {
                    now -= parr[left]; // 앞 소수 제거
                    left += 1;
                }
            }
            else if (now < n) { // 현재 now 보다 n보다 작을 경우
                right += 1; // 뒤 소수 추가
                if (right < cnt)
                    now += parr[right];
            }
            else { // 현재 now가 n일 경우
                ans += 1;

                right += 1; // 뒤 소수 추가
                if (right < cnt)
                    now += parr[right];

                now -= parr[left]; // 앞 소수 제거
                left += 1;
            }
        }

        return ans;
    } 

	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        System.out.println(solve(n));
    }
}
