package programmers.LV2_131704;

import java.util.*;

public class Solution {

    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int oIdx = 0;

        for(int box=1; box<=order.length; box++){
            boolean isPutNow = false;
            while(true){
                if(!isPutNow && order[oIdx] == box){
                    oIdx++;
                    isPutNow = true;
                }else if(!stack.isEmpty() && stack.peek() == order[oIdx]){
                    stack.pop();
                    oIdx++;
                }else{
                    break;
                }
            }

            if(!isPutNow) stack.add(box);
        }

        return oIdx;
    }

}