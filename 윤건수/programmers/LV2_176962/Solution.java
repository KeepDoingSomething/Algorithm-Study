package programmers.LV2_176962;

import java.util.*;

public class Solution {
    public String[] solution(String[][] plans) {

        PriorityQueue<Plan> plansQue =
                new PriorityQueue<>((plan1, plan2) -> plan1.startTime - plan2.startTime);
        Stack<Plan> remainPlanStack = new Stack<>();

        for(String[] plan : plans){
            plansQue.add(new Plan(plan[0], plan[1], plan[2]));
        }

        int curTime = plansQue.peek().startTime;
        int endTime = timeToMin("24:00");
        int ansIdx = 0;
        String[] answer = new String[plans.length];

        Plan curPlan = plansQue.poll();

        while(curTime++ < endTime){

            if(!curPlan.isDone()){
                curPlan.remainTime--;

                if(curPlan.isDone()){
                    answer[ansIdx++] = curPlan.subject;
                    if(!remainPlanStack.isEmpty()){
                        curPlan = remainPlanStack.pop();
                    }
                }
            }

            if(!plansQue.isEmpty() && plansQue.peek().startTime == curTime){
                if(!curPlan.isDone()){
                    remainPlanStack.push(curPlan);
                }

                curPlan = plansQue.poll();
            }
        }

        if(!curPlan.isDone()){
            answer[ansIdx++] = curPlan.subject;
        }

        while(!remainPlanStack.isEmpty()){
            answer[ansIdx++] = remainPlanStack.pop().subject;
        }

        return answer;
    }

    public int timeToMin(String time){
        String[] timeInfo = time.split(":");
        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }

    class Plan{
        String subject;
        int startTime;
        int remainTime;

        Plan(String subject, String time, String period){
            this.subject = subject;
            this.startTime = timeToMin(time);
            this.remainTime = Integer.parseInt(period);
        }

        public boolean isDone(){
            return remainTime < 1;
        }

        @Override
        public String toString(){
            return "subject: " + this.subject
                    + ", startTime: " + this.startTime
                    + ", remainTime: " + remainTime;
        }
    }
}