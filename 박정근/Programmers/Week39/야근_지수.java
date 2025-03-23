/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.23(Sun)
 * Runtime   : 1.91ms
 * Memory    : 91.1MB
 * Algorithm : parametric-search
 */

// >> 첫 번째 풀이 ( parametric-search )
// 파라매트릭 서치로 풀이했습니다. (Solve Time : 0h 45m)

// n만큼 works 배열을 줄이되, 원소마다 차이를 최대한으로 줄여야 하는 문제입니다.
// 파라매트릭 서치로, 작업했을 때 works 배열의 최대 잔여량을 구합니다. 
// n 보다 더 작업량이 나온다면, 최대 잔여량만큼 남은 원소들을 1씩 더하면서 나머지들과 같이 제곱 합해주면 답이 됩니다.

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        // 1. 파라매트릭 서치로, 작업했을 때 works 배열의 최대 잔여량을 구합니다.
        int start = 0;
        int end = max_works;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            
            // 최대 잔여량이 mid 일 때, 몇 개의 일을 처리하는가?
            int worked = 0;
            for (int w : works) worked += Math.max(0, w - mid);
            
            if (worked >= n) start = mid;
            else end = mid;
        }
    
        int start_worked = 0;
        for (int w : works) start_worked += Math.max(0, w - start);
        
        // works[i] 가 > start 인 수에, 남은 잔량 (start_worked - n) 에서 1씩 더한다.
        int cnt = start_worked - n;
        for (int w : works) {
            if (w > start && cnt > 0) { 
                answer += (long)Math.pow((start + 1), 2);
                cnt -= 1;
            }
            else {
                answer += (long)Math.pow(Math.min(start, w), 2);
            }
        }
        
        return answer;
    }
}
