package programmers.LV2_42839;

public class Solution {

    boolean[] prime = new boolean[10000000];
    boolean[] checkResult = new boolean[10000000];
    String[] number;
    int answer = 0;

    public int solution(String numbers) {

        // 에라스토테네스의체 초기화
        for(int i = 2; i<prime.length; i++){
            prime[i] = true;
        }

        for(int i=2; i<prime.length; i++){
            if(!prime[i]) continue;
            for(int j=i*2; j<prime.length; j+=i){
                prime[j] = false;
            }
        }

        number = numbers.split("");
        for(int i=0; i<number.length; i++){
            boolean[] visited = new boolean[number.length];
            visited[i] = true;

            int makeNum = Integer.parseInt(number[i]);
            checkPrime(makeNum);
            dfs(makeNum, visited);
        }

        return answer;
    }

    private void dfs(int beforeMakeNum, boolean[] visited){
        for(int i=0; i<visited.length; i++){
            if(!visited[i]){
                visited[i] = true;
                String beforeMakeNumStr = String.valueOf(beforeMakeNum);
                String targetNumStr = number[i];
                int makeNum =  Integer.parseInt(beforeMakeNumStr + targetNumStr);
                checkPrime(makeNum);
                dfs(makeNum, visited);
                visited[i] = false;
            }
        }
    }

    private void checkPrime(int n){
        if(prime[n] && !checkResult[n]) {
            checkResult[n] = true;
            answer++;
        }
    }
}