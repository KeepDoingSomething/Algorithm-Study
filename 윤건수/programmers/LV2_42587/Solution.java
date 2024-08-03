package programmers.LV2_42587;

import java.util.*;

public class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Priority> sortQueue = new PriorityQueue<>();
        for(int i = 0; i < priorities.length; i++){
            sortQueue.add(new Priority(i, priorities[i]));
        }

        Queue<Priority> queue = new LinkedList();
        for(int i = 0; i < priorities.length; i++){
            queue.add(new Priority(i, priorities[i]));
        }

        int result = 0;
        while(!sortQueue.isEmpty()){
            int nowValue = sortQueue.poll().value;
            while(nowValue != queue.peek().value){
                queue.add(queue.poll());
            }
            Priority process = queue.poll();
            result++;
            if(process.seq == location){
                break;
            }
        }

        return result;
    }

    private class Priority implements Comparable<Priority>{
        int seq;
        int value;

        Priority(int seq, int value){
            this.value = value;
            this.seq = seq;
        }

        @Override
        public int compareTo(Priority p){
            return p.value - this.value;
        }

        @Override
        public String toString(){
            return "\nvalue: " + value +", seq: " + seq;
        }
    }

}