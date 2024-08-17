/*
 * Author    : HojipKim
 * Date      : 2024.08.08(Thu)
 * Runtime   : 7244.62 ms
 * Memory    : 97 MB
 * Algorithm : 탐욕법（Greedy）
 * */
class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int idx = 0;
        for (int i = 0; i < number.length()-k; i++) {
            int maxNum = 0;
            for (int j = idx; j <= i+k; j++) {
                if (maxNum < number.charAt(j)-'0'){
                    maxNum = number.charAt(j)-'0';
                    idx = j+1;
                }
            }
            answer.append(maxNum);
        }

        return answer.toString();
    }
}

class Main {
    public static void main(String[] args) {

        Solution solution = new Solution();

        System.out.println(solution.solution("1924235784", 2));
    }
}