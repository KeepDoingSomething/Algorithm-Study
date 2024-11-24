package baekjoon.G4_1461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] bookInfo = br.readLine().split(" ");
        int bookCount = Integer.parseInt(bookInfo[0]);
        int liftCount = Integer.parseInt(bookInfo[1]);
        PriorityQueue<Integer> left = new PriorityQueue<>((num1, num2) -> num2 - num1);
        PriorityQueue<Integer> right = new PriorityQueue<>((num1, num2) -> num2 - num1);
        Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach( location -> {
            if(location < 0){
                left.add(location * -1);
            }else{
                right.add(location);
            }
        });

        int leftMax = 0;
        int rightMax = 0;
        if(!left.isEmpty()){
            leftMax = left.peek();
        }
        if(!right.isEmpty()){
            rightMax = right.peek();
        }

        int move;
        if (leftMax >= rightMax) {
            move = work(left, right, liftCount);
        }else{
            move = work(right, left, liftCount);
        }

        System.out.println(move);
    }

    private static int work(PriorityQueue<Integer> lastDirection, PriorityQueue<Integer> anotherDirection, int liftCount){
        int move = 0;
        int lastMove = lastDirection.peek();

        move += workDirection(lastDirection, liftCount);
        move += workDirection(anotherDirection, liftCount);
        move -= lastMove;
        return move;
    }

    private static int workDirection(PriorityQueue<Integer> direction, int liftCount){
        int move = 0;
        int moveNow;
        int canLift;
        while(!direction.isEmpty()){
            moveNow = direction.poll();
            canLift = liftCount - 1;
            while(canLift-- > 0 && !direction.isEmpty()){
                direction.poll();
            }
            move += 2 * moveNow;
        }

        return move;
    }

}
