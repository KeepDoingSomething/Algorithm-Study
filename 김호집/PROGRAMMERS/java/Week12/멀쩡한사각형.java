/*
 * Author    : HojipKim
 * Date      : 2024.08.08(Thu)
 * Runtime   : 0.13 ms
 * Memory    : 77.8 MB
 * Algorithm : 탐욕법（Greedy）
 * */
class Solution {
    public long solution(int w, int h) {
        long answer = 1;

        long weight = (long)w;
        long height = (long)h;

        long bigNum = Math.max(w, h);
        long smallNum = Math.min(w, h);

        while(smallNum != 0){
            long temp = bigNum % smallNum;
            bigNum = smallNum;
            smallNum = temp;
        }

        return (long)(weight * height) - (weight + height - bigNum);
    }
}
