import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);
        long left = 0L;
        long right = times[times.length-1] * (long)n;  // 이분 탐색을 하기 위해 시간 중 가장 높은 값을 탐색



        while(left <= right){
            long mid = (left + right) / 2; // 중간 시간을 찾음
            long sum = 0L;

            for(int i =0; i < times.length; i++){
                sum += mid / times[i];  // 해당 시간을 times 배열로 나누어 심사 받을 수 있는 인원을 카운팅
            }

            if(sum < n){ // 만약 카운팅 한 값이 n보다 작으면
                left = mid+1;
            }else{  // n보다 크면 심사를 모두 받을수 있지만 최솟값을 구하기 위해 answer에다 분을 저장하고 최솟값 탐색
                answer = mid;
                right = mid-1;
            }
        }
        return answer;
    }
}