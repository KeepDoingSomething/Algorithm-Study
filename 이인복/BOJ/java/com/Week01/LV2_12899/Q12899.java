package com.Week01.LV2_12899;

public class Q12899 {
    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.solution(21));
    }
}

class Solution {
    public String solution(int n) {
        int[] ans = new int[16];
        int num = 1;

        ans[0] = num;

        while(n != num) {
            for(int i = 0; i < ans.length; i++) {
                int curDigit = ans[i];

                if(curDigit == 1) {
                    ans[i] = 2;
                    break;
                } else if(curDigit == 2) {
                    ans[i] = 4;
                    break;
                } else {
                    ans[i] = 1;
                }

                int nextIdx = i + 1;

                if(ans[nextIdx] == 0) {
                    ans[nextIdx] = 1;
                    break;
                }
            }

            num++;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = ans.length - 1; i >= 0; i--) {
            if(ans[i] == 0){
                continue;
            }
            sb.append(ans[i]);
        }

        return sb.toString();
    }
}