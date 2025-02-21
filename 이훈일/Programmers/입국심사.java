import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long end = (long) times[times.length - 1] * n;
        long start = 0;
        long mid = 0;
        long answer = Long.MAX_VALUE;
        while(start <= end) {
            mid = (start + end) / 2;
            
            long count = 0;
            for(int time : times) {
                count += mid / time;
            }
            
            if(count < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
                answer = Math.min(answer, mid);
            }
        }
        
        return answer;
    }
}
