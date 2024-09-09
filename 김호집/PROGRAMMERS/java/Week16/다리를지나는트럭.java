/**
 * Author    : HojipKim
 * Date      : 2024.09.07(Sat)
 * Runtime   : 20.96 ms
 * Memory    : 84.9 MB
 * Algorithm : Queue, Dequeue, Stack
 */

import java.util.*;

class Node {
    private int truck_weight;
    private int pass_time;

    public Node(int truck_weight, int pass_time) {
        this.truck_weight = truck_weight;
        this.pass_time = pass_time;
    }

    public int getTruck_weight() {
        return truck_weight;
    }

    public int getPass_time() {
        return pass_time;
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> truckOrder = new LinkedList<>();

        Queue<Node> bridge = new LinkedList<>();

        for(int truck : truck_weights) {
            ;            truckOrder.offer(truck);
        }

        int nowTime = 0;
        int nowWeight = 0;

        Node node;

        while(!truckOrder.isEmpty() || !bridge.isEmpty()){
            nowTime++;

            if(!bridge.isEmpty() && bridge.peek().getPass_time() <= nowTime){
                Node outNode = bridge.poll();
                nowWeight -= outNode.getTruck_weight();
            }

            if(!truckOrder.isEmpty() && truckOrder.peek() + nowWeight <= weight && bridge.size() < bridge_length) {
                int truck = truckOrder.poll();
                node = new Node(truck, nowTime + bridge_length);
                bridge.offer(node);
                nowWeight += node.getTruck_weight();
            }

        }

        int answer = nowTime;
        return answer;
    }
}