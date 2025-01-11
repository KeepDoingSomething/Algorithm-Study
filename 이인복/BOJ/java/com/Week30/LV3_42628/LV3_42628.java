/**
 * Author    : Lee In Bok
 * Date      : 2025.01.11(Sat)
 * Runtime   : 323.99 ms
 * Memory    : 116 MB
 * Algorithm : Priority Queue
 */

package com.Week30.LV3_42628;

import java.util.*;

public class LV3_42628 {
    public static void main(String[] args) {

    }

    static class Solution {

        public final String INSERT = "I";

        public int[] solution(String[] operations) {
            int[] answer = new int[] {0, 0};
            PriorityQueue<Integer> maxPq = new PriorityQueue<>((a, b) -> b - a);
            PriorityQueue<Integer> minPq = new PriorityQueue<>();
            StringTokenizer st;
            boolean isMax = true;

            for(String operation : operations) {
                st = new StringTokenizer(operation);
                String op = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if(INSERT.equals(op)) {
                    maxPq.add(num);
                } else {
                    isMax = (num == 1);

                    if(isMax) {
                        moveAllElements(maxPq, minPq);
                    } else {
                        moveAllElements(minPq, maxPq);
                    }
                }
            }

            maxPq.addAll(minPq);

            if(!maxPq.isEmpty()) {
                answer[0] = maxPq.poll();
                answer[1] = answer[0];

                while(!maxPq.isEmpty()) {
                    answer[1] = maxPq.poll();
                }
            }

            return answer;
        }

        public static void moveAllElements(PriorityQueue<Integer> to, PriorityQueue<Integer> from) {
            to.addAll(from);

            if(to.isEmpty()) {
                return;
            }

            from.clear();
            to.poll();
        }
    }
}