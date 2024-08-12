package programmers.LV2_42883;

import java.util.*;

public class Solution {

    public String solution(String numbers, int k) {

        Stack<String> stack = new Stack<>();
        String[] number = numbers.split("");

        for(int i=0; i<number.length; i++){
            while(!stack.isEmpty() && k > 0 && number[i].compareTo(stack.peek()) > 0){
                stack.pop();
                k--;
            }
            stack.push(number[i]);
        }

        while(k>0){
            stack.pop();
            k--;
        }

        String result = "";

        while(!stack.isEmpty()){
            result = stack.pop() + result;
        }

        return result;
    }

}
