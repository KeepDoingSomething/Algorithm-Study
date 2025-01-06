package programmers.LV3_42628;

import java.util.*;

public class Solution {

    final String INSERT = "I";
    final String DELETE = "D";

    public int[] solution(String[] operations) {

        LinkedList<Integer> deque = new LinkedList<>();

        for(String oper: operations){
            String[] params = oper.split(" ");
            String command = params[0];
            int num = Integer.parseInt(params[1]);

            if(command.equals(INSERT)){
                deque.add(num);
                Collections.sort(deque);

            }else if(command.equals(DELETE) && !deque.isEmpty()) {
                if(num == 1){
                    deque.pollLast();
                }else if(num ==  -1){
                    deque.pollFirst();
                }

            }

        }

        return deque.isEmpty()
                ? new int[]{0, 0}
                : new int[]{deque.peekLast(), deque.peekFirst()};
    }

}