/**
 * Author    : Lee In Bok
 * Date      : 2025.02.15(Sat)
 * Runtime   : 87.08 ms
 * Memory    : 102 MB
 * Algorithm : String, implementation
 */
package com.year2025.Week34;

import java.util.Arrays;

public class LV2_17687 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(2, 4, 2, 1)
        );
    }

    static class Solution {
        public String solution(int n, int t, int m, int p) {
            StringBuilder ans = new StringBuilder();
            int maxLen = t * m;
            String[] strSeqArr = getStrSeqArr(n, maxLen);

            for(int i = p - 1; i < maxLen; i+=m) {
                ans.append(strSeqArr[i]);
            }

            return ans.toString();
        }

        public String[] getStrSeqArr(int n, int maxLen) {
            int num = 0;
            StringBuilder sb = new StringBuilder();

            while(sb.length() < maxLen) {
                sb.append(Integer.toString(num, n));

                num++;
            }

            return Arrays.stream(sb.toString().split(""))
                         .map(String::toUpperCase)
                         .toArray(String[]::new);
        }
    }
}