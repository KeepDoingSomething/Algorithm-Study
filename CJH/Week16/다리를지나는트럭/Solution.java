package programmers.level2.다리를지나는트럭;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    class Truck {
        int weight;
        int move;

        public Truck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public void moving() {
            this.move++;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int seconds = 0;
        Queue<Truck> waitQ = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            waitQ.offer(new Truck(truckWeight));
        }
        Queue<Truck> moveQ = new LinkedList<>();
        int currentWeight = 0;

        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
            seconds++;
            // 트럭 이동
            for (Truck truck : moveQ) {
                truck.moving();
            }
            // 다리를 다 건넌 트럭 제거
            if (!moveQ.isEmpty() && moveQ.peek().move > bridge_length) {
                Truck poll = moveQ.poll();
                currentWeight -= poll.weight;
            }

            // 새로운 트럭 다리에 올리기
            if (!waitQ.isEmpty() && currentWeight + waitQ.peek().weight <= weight) {
                Truck poll = waitQ.poll();
                currentWeight += poll.weight;
                moveQ.offer(new Truck(poll.weight));
            }

        }

        return seconds;
    }
}
