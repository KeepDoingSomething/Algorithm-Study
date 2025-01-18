/**
 * Author    : Lee In Bok
 * Date      : 2024.11.12(Tue)
 * Runtime   : 63.34 ms
 * Memory    : 139 MB
 * Algorithm : Implement, Stack
 */

package com.year2024.Week24.LV2_131704;

import java.util.*;

public class LV2_131704 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                //sol.solution(new int[] {4, 3, 1, 2, 5})  // 2
                //sol.solution(new int[] {5, 4, 3, 2, 1})  // 5
                //sol.solution(new int[] {3, 5, 4, 2, 1})  // 5
                sol.solution(new int[] {2, 1, 4, 3, 6, 5, 8, 7, 10, 9})  // 10
        );
    }

    static class Solution {
        public int solution(int[] orders) {
            Stack<Integer> sub = new Stack();
            int ans = 0;
            int seq = 1;

            for(int order : orders) {
                for(int i = seq; i < order ; i++) {  // 정해진 순서와 같을 때 까지 증가
                    sub.push(i);
                    seq = i + 1;
                }

                boolean isOnSub = !sub.isEmpty() && sub.peek() == order;

                if(seq == order) {
                    seq++;
                    ans++;
                } else if(isOnSub) {
                    sub.pop();
                    ans++;
                } else {
                    break;
                }
            }

            return ans;
        }
    }
}
