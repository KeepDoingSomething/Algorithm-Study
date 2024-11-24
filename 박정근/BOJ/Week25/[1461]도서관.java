/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.18(Mon)
 * Runtime   : 108 ms
 * Memory    : 14280 KB
 * Algorithm : Greedy + Sort
 */

// >> 첫 번째 풀이 ( 그리디 + 정렬 )
// 1. 책을 놓을 위치들을 음수 / 양수로 구분한다.
// 2. 책을 놓을 위치 중 가장 먼 곳을 고른 뒤, 그 먼 곳은 마지막에 M권만큼 가져가서 끝낸다고 생각하고 M개의 위치는 제외한다. (오고 가면 거리가 2배가 되므로 가장 먼 곳은 한 번만 가는게 효율적이다.)
// 3. 가장 먼 곳을 제외한 나머지 위치들은 음수 / 양수 위치 따로, M권씩 옮긴다. (M권씩 옮길 때 현재 가장 먼 곳 * 2 만큼의 거리를 이동한다.)

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
      int m = Integer.parseInt(st.nextToken());

      int[] arr = new int[n+1];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
      arr[n] = 0; // 배열에 0 을 덧붙여, 음수 / 양수 책 위치들의 끝을 표시한다.
      Arrays.sort(arr); // 배열을 정렬하면 [음수 쪽 위치들 / 0 / 양수 쪽 위치들] 의 양상을 보인다.

      int answer = 0;
      int left = 0; // 음수 쪽에서 현재 가장 먼 위치의 인덱스
      int right = n; // 양수 쪽에서 현재 가장 먼 위치의 인덱스
      int cnt = 0;
    
      if (Math.abs(arr[left]) <= Math.abs(arr[right])) { // 가장 먼 곳이 양수 쪽에 있을 때
        answer += Math.abs(arr[right]); // 양수 쪽에서 가장 먼 곳까지의 거리만큼 이동
        cnt = m;
        while (arr[right] != 0 && cnt > 0) { // M 권만큼 옮기기. (0에 다다를 때 까지)
          cnt -= 1;
          right -= 1;
        }
      }
      else { // 가장 먼 곳이 음수 쪽에 있을 때
        answer += Math.abs(arr[left]); // 음수 쪽에서 가장 먼 곳까지의 거리만큼 이동
        cnt = m;
        while (arr[left] != 0 && cnt > 0) { // M 권만큼 옮기기. (0에 다다를 때 까지)
          cnt -= 1;
          left += 1;
        }
      }

      while (arr[left] != 0) { // 음수 쪽에서 M권씩 옮기기
        answer += Math.abs(arr[left]) * 2; // 현재 음수 쪽에서 가장 먼 곳까지의 거리 * 2 만큼 이동
        cnt = m;
        while (arr[left] != 0 && cnt > 0) { // M 권만큼 옮기기. (0에 다다를 때 까지)
          cnt -= 1;
          left += 1;
        }
      }
      while (arr[right] != 0) { // 양수 쪽에서 M권씩 옮기기
        answer += Math.abs(arr[right]) * 2; // 현재 양수 쪽에서 가장 먼 곳까지의 거리 * 2 만큼 이동
        cnt = m;
        while (arr[right] != 0 && cnt > 0) { // M 권만큼 옮기기. (0에 다다를 때 까지)
          cnt -= 1;
          right -= 1;
        }
      }

      System.out.println(answer);
    }
}
