package programmers.LV2_42584;

public class Solution {

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i=0; i<prices.length; i++){
            int currentPrice = prices[i];
            int period = 0;
            for(int j=i+1; j<prices.length; j++){
                int comparePrice = prices[j];
                period++;
                if(currentPrice > comparePrice){
                    break;
                }
            }
            answer[i] = period;
        }
        return answer;
    }

}