package com.year2024.Week09.LV2_150369;

import java.util.*;

public class LV2_Q150369 {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            long ans = 0;
            Stack<Integer> D = new Stack<>();
            Stack<Integer> P = new Stack<>();

            for(int i = 0; i < n; i++) {
                D.push(deliveries[i]);
                P.push(pickups[i]);
            }

            while(!D.isEmpty() || ! P.isEmpty()) {
                removeZero(D);
                removeZero(P);

                ans += 2 * Math.max(D.size(), P.size());

                checkQuantities(D, cap);
                checkQuantities(P, cap);
            }

            return ans;
        }

        public void checkQuantities(Stack<Integer> stack, int cap) {
            int cnt = 0;

            while(!stack.isEmpty()) {
                cnt += stack.pop();

                if(cnt >= cap) {
                    stack.push(cnt - cap);  // 남은거 넣어둠
                    break;
                }
            }
        }

        public void removeZero(Stack<Integer> stack) {
            while(!stack.isEmpty() && stack.peek() == 0) {
                stack.pop();
            }
        }
    }
}
