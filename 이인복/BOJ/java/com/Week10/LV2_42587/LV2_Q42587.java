package com.Week10.LV2_42587;

import java.util.*;

public class LV2_Q42587 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(new int[]{1,1,9,1,1,1}, 0)
        );

        // new int[]{1,2,3,4,5}, 2 => 3
        // new int[]{5,4,3,2,1}, 0 => 1
        // new int[]{2,3,3,2,9,3,3}, 3 => 6
        // new int[]{1,1,1}, 1 => 2
        // new int[]{1,1,9,1,1,1}, 0 => 5
    }

    static class Solution {
        public int solution(int[] priorities, int location) {
            Queue<Node> queue = new PriorityQueue<>();
            Queue<Node> deque = new LinkedList<>();
            int ans = 1;

            for(int i = 0; i < priorities.length; i++) {
                Node node = new Node(priorities[i], i);

                queue.add(node);
                deque.add(node);
            }

            while(!queue.isEmpty()) {
                Node pqNode = queue.poll();

                while(pqNode.val != deque.peek().val) {
                    deque.add(deque.poll());
                }

                Node deqNode = deque.poll();

                if(deqNode.loc == location) {
                    break;
                }

                ans++;
            }

            return ans;
        }

        class Node implements Comparable<Node>{
            int val;
            int loc;

            public Node(int val, int loc) {
                this.val = val;
                this.loc = loc;
            }

            @Override
            public int compareTo(Node node) {
                return node.val - this.val;
            }
        }
    }
}
