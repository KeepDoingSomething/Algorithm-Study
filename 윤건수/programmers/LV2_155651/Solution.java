package programmers.LV2_155651;

import java.util.*;

public class Solution {
    public int solution(String[][] book_time) {

        PriorityQueue<Time> inQue = new PriorityQueue<>();
        PriorityQueue<Time> outQue = new PriorityQueue<>();

        for(String[] time : book_time){
            String[] in = time[0].split(":");
            String[] out = time[1].split(":");
            inQue.add(new Time(Integer.parseInt(in[0]), Integer.parseInt(in[1])));

            Time outTime = new Time(Integer.parseInt(out[0]), Integer.parseInt(out[1]));
            outTime.addMin(10);
            outQue.add(outTime);
        }

        Time curTime = new Time(0, 0);
        Time endTime = new Time(24, 0);

        int curRoom = 0;
        int answer = 0;
        while(!curTime.equals(endTime)){

            while(!outQue.isEmpty() && outQue.peek().equals(curTime)){
                Time out = outQue.poll();
                curRoom--;
            }

            while(!inQue.isEmpty() && inQue.peek().equals(curTime)){
                Time in = inQue.poll();
                curRoom++;
            }

            answer = Math.max(answer, curRoom);
            curTime.addMin(1);
        }

        return answer;
    }

    private class Time implements Comparable<Time>{

        int hour;
        int min;

        Time(int hour, int min){
            this.hour = hour;
            this.min = min;
        }

        public void addMin(int add){
            if(this.min + add >= 60){
                this.hour++;
                this.min = min + add - 60;
            }else{
                this.min += add;
            }
        }

        @Override
        public int compareTo(Time t){
            return this.hour - t.hour == 0
                    ? this.min - t.min
                    : this.hour - t.hour;
        }

        @Override
        public String toString(){
            return this.hour + ":" + this.min;
        }

        @Override
        public boolean equals(Object o){
            Time t = (Time)o;
            return this.hour == t.hour && this.min == t.min;
        }

    }

}