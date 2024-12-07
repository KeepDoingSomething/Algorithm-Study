/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.7(Sat)
 * Runtime   : 36.42ms
 * Memory    : 144MB
 * Algorithm : parametric_search
 */

// >> 첫 번째 풀이 ( 매개 변수 탐색 )
// 파라매트릭 서치로 풀이했습니다. (Solve Time : 0h 14m)
// 가장 쉬운 난이도 (diffs[0] = 1) 부터, 가장 어려운 난이도까지 탐색하며, 시간 제한보다 오래 걸리지 않는 최소 숙련도를 구합니다.

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;

        int start = 1; // 가장 쉬운 난이도 (diffs[0]) 부터

        int end = 1;
        for (int i = 1; i < n; i++) end = Math.max(end, diffs[i]); // 가장 어려운 난이도까지
        
        while (start < end) {
            int mid = (start + end) / 2;
            long now = times[0]; // 0번째 퍼즐은 풀이하고,
            for (int i = 1; i < n; i++) {
                // 다음 퍼즐 풀이 시간 더하기
                now += (Math.max(0, (diffs[i] - mid)) * (times[i-1] + times[i])) + times[i];
            }
            
            // 제한보다 큰 시간이 걸린다면 숙련도 올리기
            if (limit < now) start = mid + 1;
            else end = mid;
        }
        
        return start;
    }
}
