package programmers.LV2_42586;

import java.util.*;

public class Solution {

    Queue<Work> workQue = new LinkedList();
    ArrayList<Integer> answerList = new ArrayList<>();

    public int[] solution(int[] progresses, int[] speeds) {

        for(int i=0; i<progresses.length; i++){
            workQue.add(new Work(progresses[i], speeds[i]));
        }

        while(!workQue.isEmpty()){
            allWorkForDay();
            dayCheck();
        }

        int[] answer = new int[answerList.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public void allWorkForDay(){
        for(int i=0; i<workQue.size(); i++){
            Work work = workQue.poll();
            work.workForDay();
            workQue.add(work);
        }
    }

    public void dayCheck(){
        int todayDone = 0;
        Work firstWork = workQue.peek();

        while(!workQue.isEmpty() && firstWork.isComplete()){
            todayDone++;
            workQue.poll();

            if(!workQue.isEmpty()){
                firstWork = workQue.peek();
            }
        }

        if(todayDone > 0){
            answerList.add(todayDone);
        }
    }

    public static class Work {

        int progress;
        int speed;

        Work(int progress, int speed){
            this.progress = progress;
            this.speed = speed;
        }

        public void workForDay(){
            this.progress += speed;
        }

        public boolean isComplete(){
            return this.progress >= 100;
        }

    }

}