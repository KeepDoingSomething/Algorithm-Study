/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.25(Mon)
 * Runtime   : 516ms
 * Memory    : 118692KB
 * Algorithm : Two Pointer
 */

// >> 첫 번째 풀이 ( 투 포인터 )
// 주 아이디어 : 짝수들을 건너뛰고, 연속된 홀수 수열들을 지울 때 홀수를 최대 K개만큼 제거하면, 제거한 홀수 부분과 이웃하는 연속한 짝수 부분들을 서로 이어서 길게 만들 수 있습니다.
// 먼저, 주어진 수열을 짝수 부분 개수 / 홀수 부분 개수 / 짝 ... 짝 / 홀 / 짝 으로 가공해서, 홀수 부분을 지웠을 때 양옆에 있는 짝수 부분을 파악할 수 있도록 처리했습니다.
// -> 맨 처음과 맨 마지막에 홀수 부분이 있다면 생략으로 처리해도 됩니다. (그 부분은 지우면 비효율적입니다. 각각 맨 끝에서 두 번째 홀수 부분을 지우면 얻을 수 있기 때문입니다.)
// 현재 수열에서 아무것도 지우지 않았을 때, 짝수 부분 중 최대 길이 부분을 구했습니다. (홀수 부분들이 전부 K개보다 많은 홀수를 가지고 있을 수 있습니다.)
// 그리고, 투 포인터를 사용해 홀수를 최대 K번 지웠을 때 얻을 수 있는 최대 길이 짝수 수열을 구했습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[][] cnt_arr = new int[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(cnt_arr[i], 0);

        // 1) 짝수 부분 개수 / 홀수 부분 개수 / 짝 / 홀 ... 배열 만들기
        int mode = 0; // 0 : 짝수 부분 / 1 : 홀수 부분
        int idx = 0;
        int cnt = 1;
        for (int i = 0; i < n; i++) {
            if (i == 0) mode = arr[i] % 2;
            else {
                if (mode != (arr[i] % 2)) { // 짝수 -> 홀수 / 홀수 -> 짝수로 바뀔 때
                    if ((mode == 0) || (cnt_arr[idx][0] != 0)) { // 제일 처음 등장하는 홀수 부분을 빼는 조건입니다. 처음 부분에는 짝수 부분을 먼저 넣고 홀수 부분을 넣을 수 있도록 해줍니다.
                        cnt_arr[idx][mode] = cnt;
                        if (mode == 1) idx += 1; // 홀수 부분을 넣었다면 다음 인덱스로 넘어갑니다.
                    }

                    cnt = 1;
                    mode ^= 1;
                }
                else cnt += 1;
            }
        }
        cnt_arr[idx][mode] = cnt; 

        // 2) 아무것도 지우지 않았을 때, 짝수 부분들 중 최대 길이 구하기
        int res = 0;
        for (int i = 0; i <= idx; i++) res = Math.max(res, cnt_arr[i][0]); // 현재 짝수 부분들 중 최대 길이를 구해놓습니다.

        // 3) 투 포인터로, 홀수를 최대 K번 지웠을 때 얻을 수 있는 최대 길이 짝수 수열 구하기
        if (n >= 2) { // n이 1 이하면 64행에서 오류가 나기도 하고, 59행에서 이미 정답을 구했을 것이기에 조건을 달아줍니다.
            int start = 0;
            int end = 0;
            int now = cnt_arr[0][0] + cnt_arr[1][0]; // 현재 얻은 짝수 부분 개수. (0번째 홀수 부분을 제거했다면, 0번째 짝수 부분과 1번째 짝수 부분을 얻을 수 있습니다.)
            int need = cnt_arr[0][1]; // 현재 지워야 하는 홀수 개수 (0번째 홀수 부분에서의 홀수 개수만큼 지우는 것이 필요합니다.)

            while (true) {
                if (need > k) { // 만약 현재 k개보다 많이 지워야 한다면.. 앞에 있는 홀수 부분 하나를 지우지 않습니다. (당깁니다.)
                    now -= cnt_arr[start][0]; // start번째 홀수 부분을 지우지 않는다면, start번째 짝수 부분은 얻을 수 없습니다. [start+1..]번째 짝수 부분만 남습니다.
                    need -= cnt_arr[start][1]; // start번째 홀수 부분에서의 홀수 개수만큼은 지우지 않아도 됩니다.
                    start += 1; 
                    
                    if (start > end) { // 만약 원래 홀수 부분 하나만 지우는 거였는데 start를 당겼다면, 뒤에 있는 홀수 부분 하나를 더 지웁니다. (밉니다.)
                        end += 1;
                        if (end > idx) break;
                        now += cnt_arr[end+1][0]; // end번째 홀수 부분을 지운다면, [..end]번째 짝수 부분과 end+1번째 짝수 부분을 얻을 수 있습니다.
                        need += cnt_arr[end][1]; // end번째 홀수 부분에서의 홀수 개수만큼 지우는 것이 필요합니다.
                    }
                }
                else {
                    res = Math.max(res, now); // 최대 k만큼 지울 수 있으므로, 현재 정답과 now 중 최댓값을 정답으로 대입합니다.
                    
                    end += 1; // 더 지워도 될 것 같으니, 뒤에 있는 홀수 부분 하나를 더 지웁니다. (밉니다.)
                    if (end > idx) break;
                    now += cnt_arr[end+1][0];
                    need += cnt_arr[end][1];
                }
            }
        }

        System.out.println(res);
    }
}
