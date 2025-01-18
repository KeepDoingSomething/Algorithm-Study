/**
 * Author    : Lee In Bok
 * Date      : 2024.11.03(Sun)
 * Runtime   : 0.46ms
 * Memory    : 52.6MB
 * Algorithm : Two Pointer
 */

package com.year2024.Week23.LV2_12924;

public class LV2_12924 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(15)
        );
    }

    static class Solution {
        public int solution(int n) {
            int l = 1;  // left
            int sum = 0;
            int ans = 0;

            for(int r = 1; r <= n; r++) {  // r: rigth
                sum += r;

                while(sum >= n) {
                    if(sum == n) {
                        ans++;
                        break;
                    }

                    sum -= l;
                    l++;
                }
            }

            return ans;
        }
    }
}
