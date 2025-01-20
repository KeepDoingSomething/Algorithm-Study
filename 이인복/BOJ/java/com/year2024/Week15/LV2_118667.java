package com.year2024.Week15;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LV2_118667 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(new int[]{1, 1}, new int[]{1, 5})
        );
    }

    static class Solution {
        public int solution(int[] queue1, int[] queue2) {
            int ans = 0;
            int repeatCnt = (queue1.length + queue2.length) + 100;
            long sum1 = Arrays.stream(queue1).sum();
            long sum2 = Arrays.stream(queue2).sum();
            Queue<Long> q1 = new LinkedList();
            Queue<Long> q2 = new LinkedList();

            for(int num : queue1) {
                q1.add((long) num);
            }

            for(int num : queue2) {
                q2.add((long) num);
            }

            if(sum1 == sum2) return ans;

            for(int i = 0; i < repeatCnt; i++) {
                if(!q1.isEmpty() && sum1 > sum2) {
                    long front = q1.poll();

                    q2.add(front);
                    sum1 -= front;
                    sum2 += front;
                } else if(!q2.isEmpty() && sum1 < sum2) {
                    long front = q2.poll();

                    q1.add(front);
                    sum1 += front;
                    sum2 -= front;
                } else if(sum1 == sum2) {
                    return ans;
                }

                ans++;
            }

            return -1;
        }
    }
}
