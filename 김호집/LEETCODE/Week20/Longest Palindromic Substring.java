/**
 * Author    : HojipKim
 * Date      : 2024.10.19(Sat)
 * Runtime   : 13 ms
 * Memory    : 42.19 MB
 * Algorithm : 너비 우선 탐색, 이분 그래프, 깊이 우선 탐색, 그래프 이론, 그래프 탐색
 */

class Solution {

    int idx, maxLength;

    public String longestPalindrome(String s) {
        int length = s.length();
        if(length < 2){
            return s;
        }

        for(int i = 0 ; i  < length - 1 ; i++){
            find(s,i,i);
            find(s,i,i+1);
        }
        return s.substring(idx,idx+maxLength);

    }

    // 특정 index로부터 좌우 탐색
    public void find(String str, int i, int j){
        while(i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)){
            i--;
            j++;
        }
        if(maxLength < j-i-1){
            idx = i+1;
            maxLength = j - i -1;
        }
    }
}