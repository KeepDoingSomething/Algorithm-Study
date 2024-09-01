package programmers.LV2_118867;

import java.util.*;

public class Solution {

    int maxTry;

    public int solution(int[] queue1, int[] queue2) {
        Queue<Long> q1 = arrayToQue(queue1);
        Queue<Long> q2 = arrayToQue(queue2);
        long q1Sum = calcSum(queue1);
        long q2Sum = calcSum(queue2);
        long sumGoal = q1Sum + q2Sum;
        if(sumGoal % 2 != 0) return -1;
        sumGoal /= 2;

        int answer = 0;
        while(q1Sum != sumGoal){
            answer++;
            if(!q1.isEmpty() && q1Sum > q2Sum){
                long poll = q1.poll();
                q2.add(poll);
                q1Sum -= poll;
                q2Sum += poll;
            }else if(!q2.isEmpty() && q2Sum > q1Sum){
                long poll = q2.poll();
                q1.add(poll);
                q1Sum += poll;
                q2Sum -= poll;
            }

            if(answer == queue1.length * 3){
                answer = -1;
                break;
            }
        }

        return answer;
    }

    private Queue<Long> arrayToQue(int[] queue){
        Queue<Long> ret = new LinkedList<>();
        for(int a : queue){
            ret.add((long)a);
        }
        return ret;
    }

    private long calcSum(int[] queue){
        long ret = 0;
        for(int a : queue){
            ret += (long)a;
        }
        return ret;
    }

}