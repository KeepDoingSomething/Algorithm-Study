import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {

        String answer = "";

        StringBuilder sb = new StringBuilder(); // 동기화가 필요없다고 판단하여 StringBuilder 사용

        StringBuilder answerSb = new StringBuilder();

        int number = 0;

        String changeNumber = "";

        while(true){

            StringBuilder resultSb = new StringBuilder();

            if(n==2){
                changeNumber = Integer.toString(number, n);
            }
            else{
                changeNumber = Integer.toString(number, n).toUpperCase(); //숫자를 진법으로 변환
            }

            sb.append(changeNumber); // 진법 변환 숫자를 더해줌

            String result =  sb.toString();

            for(int i = p-1; i < result.length(); i += m){
                resultSb.append(result.charAt(i));
            }

            if(resultSb.length() >= t){

                for(int i =0; i < t; i++){
                    String str =  resultSb.toString();

                    answerSb.append(str.charAt(i));

                }
                answer = answerSb.toString();
                break;
            }
            number++;

        }
        return answer;
    }
}