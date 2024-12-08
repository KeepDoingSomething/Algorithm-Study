/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.18(Mon)
 * Runtime   : 101.21 ms
 * Memory    : 94.8 MB
 * Algorithm : Bitmask + Brute Force
 */

// >> 첫 번째 풀이 ( 비트마스킹 + 완전 탐색 )
// Week24 에서 풀었던 양궁대회와 비슷한 원리로 풀이할 수 있습니다.
// 주어지는 숫자의 개수는 최대 20개이므로, 0부터 2^20 까지 탐색하여, 비트 이론으로 i번째 비트가 0이면 i번째 수를 더하고, 1이면 i번째 수를 빼는 것으로 처리했습니다.

// 이 코드에서는 모든 숫자의 합을 구해놓고 i번째 비트가 1이면 그때 (i번째 수 << 1) 을 빼도록 하였습니다. 
// ( 1. i번째 수를 빼기 위해선 이미 더한 값을 빼고 한 번 더 수를 빼야 함. )
// ( 2. i번째 수 * 2 보다 i번째 수 << 1 이 더 빠릅니다. (결과는 동일합니다.) )

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        int n = numbers.length;
        int end = (int)Math.pow(2, n);
        
        int sums = 0;
        for (int e : numbers) sums += e;
        
        for (int i = 0; i < end; i ++) {
            int now = sums;
            for (int j = 0; j < n; j ++) {
                if ((int)(i & (1 << j)) != 0) now -= (numbers[j] << 1);
            }
            if (now == target) answer += 1;
        }
        
        return answer;
    }
}
