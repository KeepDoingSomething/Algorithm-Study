package programmers.LV2_12924;

public class Solution {
    public int solution(int n) {
        int low = 0;
        int high = 1;
        int sum = 1;
        int answer = 0;
        while(high <= n){
            if(sum == n){
                answer++;
                high++;
                sum+=high;
            } else if (sum >= n){
                sum -= low;
                low++;
            }else {
                high++;
                sum += high;
            }
        }
        return answer;
    }
}