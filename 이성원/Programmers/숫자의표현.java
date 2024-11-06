class Solution {
    public int solution(int n) {
        int answer = 0;

        for(int i =1; i <= n; i++){

            int sum =0;

            sum += i;

            if(sum == n){
                answer++;
                continue;
            }

            for(int j = i+1; j <=n; j++){
                sum+=j;
                if(sum == n){
                    answer++;
                    break;
                }

                if(sum > n){
                    break;
                }
            }
        }

        return answer;

    }
}