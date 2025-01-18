package com.year2024.Week16;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LV2_42583 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            //sol.solution(2, 10, new int[]{7,4,5,6})
            //sol.solution(100, 100, new int[]{10})
            sol.solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10})
        );
    }

    static class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            Stack<Truck> waitingTrucks = new Stack<>();
            Bridge bridge = new Bridge();

            for(int i = truck_weights.length - 1; i >= 0; i--) {
                waitingTrucks.push(new Truck(truck_weights[i], 0));
            }

            while(!waitingTrucks.isEmpty() || !bridge.onBridge.isEmpty()) {
                bridge.time++;  // 시간 증가

                // 맨 앞 트럭이 다리를 건널 수 있다면
                if(!bridge.onBridge.isEmpty() && bridge.onBridge.peek().dist == bridge_length) {
                    Truck truck = bridge.onBridge.poll();

                    bridge.totalWeight -= truck.weight;
                }

                // 대기 중인 다음 트럭의 무게가 현재 다리 무게의 총량과 합 해졌을 때 더 추가될 수 있다면
                if(!waitingTrucks.isEmpty() && waitingTrucks.peek().weight + bridge.totalWeight <= weight) {
                    Truck truck = waitingTrucks.pop();

                    bridge.onBridge.add(truck);
                    bridge.totalWeight += truck.weight;
                }

                bridge.onBridge.stream().forEach(Truck::moveTruck);  // 다리 위 모든 트럭 시간 증가
            }

            return bridge.time;
        }
    }

    static class Bridge {
        int time = 0;
        int totalWeight = 0;
        Queue<Truck> onBridge = new LinkedList<>();

        public Bridge() {}
    }

    static class Truck {
        final int weight;
        int dist;

        public Truck(int weight, int dist) {
            this.weight = weight;
            this.dist = dist;
        }

        public void moveTruck() {
            this.dist++;
        }
    }
}