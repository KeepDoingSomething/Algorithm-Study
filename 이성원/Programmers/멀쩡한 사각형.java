class Solution {
    public long solution(long w, long h) {
        long answer = 1;

        long line = w + h;

        long diagoal =  gcd(w,h);

        long result = line - diagoal;

        answer = (w*h) - result;
        return answer;
    }


    public long gcd(long num1, long num2){
        if(num1 % num2 == 0) {
            return num2;
        }
        return gcd(num2, num1 % num2);
    }
}