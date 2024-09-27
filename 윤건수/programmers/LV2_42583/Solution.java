package programmers.LV2_42583;

import java.util.*;

public class Solution {
    public int solution(int bridgeLength, int maxWeight, int[] truckWeights) {

        Stack<Truck> weightTruck = new Stack<>();
        Queue<Truck> bridgeTruck = new LinkedList<>();
        for(int i=truckWeights.length-1; i>=0; i--){
            weightTruck.push(new Truck(bridgeLength, truckWeights[i]));
        }

        int bridgeWeight = 0;
        int time = 0;
        while(!weightTruck.isEmpty() || !bridgeTruck.isEmpty()){
            time++;

            int size = bridgeTruck.size();
            for(int i=0; i<size; i++){
                Truck truck = bridgeTruck.poll();
                bridgeWeight -= truck.weight;
                truck.move();
                if(!truck.isArrive()){
                    bridgeTruck.add(truck);
                    bridgeWeight += truck.weight;
                }
            }

            Truck newTruck = null;
            if(!weightTruck.isEmpty()
                    && (maxWeight >= (weightTruck.peek().weight + bridgeWeight))
                    && (bridgeTruck.size() <= bridgeLength)
            ){
                newTruck = weightTruck.pop();
                bridgeWeight += newTruck.weight;
                bridgeTruck.add(newTruck);
                newTruck.move();
            }
        }

        return time;
    }

    private class Truck{

        int remainLength;
        int weight;

        Truck(int remainLength, int weight){
            this.remainLength = remainLength;
            this.weight = weight;
        }

        private void move(){
            this.remainLength--;
        }

        private boolean isArrive(){
            return remainLength < 0;
        }
    }
}