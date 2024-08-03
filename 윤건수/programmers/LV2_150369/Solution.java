package programmers.LV2_150369;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/150369#

public class Solution {

    long answer;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        Stack<Point> deliveryStack = new Stack<>();
        Stack<Point> pickupStack = new Stack<>();
        answer = 0;

        for(int i = 0; i < n; i++){
            int distance = i + 1;
            int deliveryBox = deliveries[i];
            int pickupBox = pickups[i];

            if(deliveryBox > 0) deliveryStack.add(new Point(distance, deliveryBox));
            if(pickupBox > 0) pickupStack.add(new Point(distance, pickupBox));
        }

        while(!deliveryStack.isEmpty() || !pickupStack.isEmpty()){
            cycle(deliveryStack, pickupStack, cap);
        }

        return answer;
    }

    private void cycle(Stack<Point> deliveryStack, Stack<Point> pickupStack, int cap){
        int distance = 0;
        if(!deliveryStack.isEmpty()){
            distance = deliveryStack.peek().distance;
        }
        if (!pickupStack.isEmpty()) {
            distance = Math.max(pickupStack.peek().distance, distance);
        }
        answer += distance * 2;

        checkStack(deliveryStack, cap);
        checkStack(pickupStack, cap);
    }

    private void checkStack(Stack<Point> stack, int cap) {
        while(!stack.isEmpty() && cap != 0){
            Point point = stack.pop();
            if(cap >= point.remain){
                cap -= point.remain;
                point.remain = 0;
            }else{
                point.remain -= cap;
                cap = 0;
                stack.push(point);
            }
        }
    }

    private class Point{
        int distance;
        int remain;

        public Point(int distance, int remain){
            this.distance = distance;
            this.remain = remain;
        }
    }

}