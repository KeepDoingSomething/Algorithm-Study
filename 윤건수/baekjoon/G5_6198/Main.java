package baekjoon.G5_6198;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 옥상 정원 꾸미기 (Gold 5)
// https://www.acmicpc.net/problem/6198

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        int buildingNum = Integer.parseInt(br.readLine());
        String line;
        long answer = 0;
        stack.push(Integer.valueOf(br.readLine()));

        while ((line = br.readLine()) != null) {
            int size = Integer.parseInt(line);
            while(!stack.isEmpty() && stack.peek() <= size){
                stack.pop();
            }
            answer += stack.size();
            stack.push(size);
        }

        System.out.println(answer);
    }

}
