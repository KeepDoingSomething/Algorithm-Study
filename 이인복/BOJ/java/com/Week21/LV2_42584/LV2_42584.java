/**
 * Author    : Lee In Bok
 * Date      : 2024.10.19(Sat)
 * Runtime   : 36.84ms
 * Memory    : 80.6MB
 * Algorithm : Stack
 */

package com.Week21.LV2_42584;

import java.util.*;

public class LV2_42584 {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            Stack<Node> stack = new Stack();

            for(int i = 0; i < prices.length; i++) {
                Node node = new Node(prices[i], i);

                while(!stack.isEmpty() && stack.peek().price > node.price) {
                    Node pop = stack.pop();

                    answer[pop.idx] = i - pop.idx;
                }

                stack.push(node);
            }

            if(!stack.isEmpty()) {
                Node topNode = stack.pop();
                answer[topNode.idx] = 0;

                while(!stack.isEmpty()) {
                    Node pop = stack.pop();

                    answer[pop.idx] = topNode.idx - pop.idx;
                }
            }

            return answer;
        }
    }

    static class Node {
        int price;
        int idx;

        Node(int price, int idx) {
            this.price = price;
            this.idx = idx;
        }
    }
}
