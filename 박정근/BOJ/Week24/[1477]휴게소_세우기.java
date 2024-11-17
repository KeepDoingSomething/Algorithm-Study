/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.15(Fri)
 * Runtime   : 104 ms
 * Memory    : 14248 KB
 * Algorithm : Brute Force
 */

// >> 첫 번째 풀이 ( 완전 탐색 )
// 처음에는 가장 긴 구간 사이 중간 지점에 휴게소를 설치하는 방법을 떠올렸으나, 최적의 방법이 아님을 알게 되었습니다.
// 0부터 100까지의 구간에 휴게소를 2개 설치한다고 가정하면, [33, 33, 34] 로 나누는게 최적이지만, 중간 지점 설치 아이디어는 [25, 25, 50] 구간이 만들어지기 때문입니다.
// 입력으로 들어온, 위치를 바꿀 수 없는 휴게소들은 그대로 두고, 최대 길이 구간을 최적으로 줄이는 방법을 떠올리고자 했습니다.
// 그 결과, 입력으로 들어온 휴게소는 건드릴 수 없으니, 새로 설치한 휴게소의 위치를 계속 변경하면서 균일하게 구간을 나누자라는 생각이 들었습니다.

// 입력으로 들어온 휴게소 배열 arr과는 달리, section 배열을 하나 만들어 arr[i] - arr[i-1] 사이에 몇 개의 휴게소가 새로 설치되어 있는지를 기록했습니다.
// 전체 구간을 돌면서, 어떤 구간이 최대 길이 구간인지 찾고, 그 구간에 휴게소를 하나 더 설치하였습니다.
// 이때, 휴게소 개수에 맞게끔 그 구간을 균일하게 나누도록 처리했습니다. 즉, [50, 50] 으로 나눈 구간에 휴게소를 하나 더 설치하면 [33, 33, 34] 나눈 것처럼 처리하는 겁니다.

// 처리하는 방법은 간단합니다. section[i] 를 1 추가해서 이 구간에 휴게소를 하나 더 설치했다고 기록하고, 수학으로 처리합니다. 
// 현재 나눠진 이 구간 안의 최대 길이 구간 : ((arr[i] - arr[i-1]) / (section[i] + 1)) + (((arr[i] - arr[i-1]) % (section[i] + 1) != 0) ? 1 : 0)

// 다음 휴게소를 설치할 때도 전체 구간을 돌텐데, section 배열에 기록한 개수만큼 구간을 최적으로 나누어 현재 나눠진 이 구간 안의 최대 길이 구간이 전체의 최대 길이 구간인지 판단하도록 처리했습니다.
// 완전 탐색이지만 시간 복잡도는 O(NM) 으로, 아주 충분합니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        
        if (n == 0) System.out.println((l / (m + 1)) + ((l % (m + 1) != 0) ? 1 : 0)); // n이 0이면, 0부터 l까지의 구간을 (m + 1) 로 나눴을 때의 최대 구간을 출력한다.
        else {
            st = new StringTokenizer(br.readLine());
          
            int[] arr = new int[n+2];  // 0 ~ l 까지의 휴게소 구간 배열
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
            arr[n] = 0;
            arr[n+1] = l;
            Arrays.sort(arr);

            int[] section = new int[n+2]; // 구간마다 세운 새로운 휴게소 개수
            Arrays.fill(section, 0);

            int max_section = 0; // 현재 최대 길이 구간
            int max_idx = 0; // 현재 최대 길이 구간의 인덱스
            for (int i = 0; i < m + 1; i++) { // 휴게소를 m번 설치하고, m+1번 째에는 max_section 만 얻어오기 위함
                max_section = 0;
                max_idx = 0;

                for (int j = 1; j < n + 2; j++) {
                    if (section[j] == 0) { // 이 구간에 휴게소가 설치되지 않았을 때
                        if (max_section < arr[j] - arr[j-1]) {
                            max_section = arr[j] - arr[j-1];
                            max_idx = j;
                        }
                    }
                    else { // 이 구간에 휴게소가 설치되었을 때, 휴게소를 하나 더 균일하게 설치했을 때가 최대 길이 구간이라면
                        if (max_section < ((arr[j] - arr[j-1]) / (section[j] + 1)) + (((arr[j] - arr[j-1]) % (section[j] + 1) != 0) ? 1 : 0)) {
                            max_section = ((arr[j] - arr[j-1]) / (section[j] + 1)) + (((arr[j] - arr[j-1]) % (section[j] + 1) != 0) ? 1 : 0);
                            max_idx = j;
                        }
                    }
                }
                section[max_idx] += 1; // 최대 길이 구간인 곳에 휴게소 하나 더 설치
            }
            
            System.out.println(max_section); // 현재 최대 길이 구간 출력
        }
    }
}
