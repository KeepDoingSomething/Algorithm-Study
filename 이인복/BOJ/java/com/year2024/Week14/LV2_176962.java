package com.year2024.Week14;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class LV2_176962 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] ans = sol.solution(new String[][]{
                {"science", "12:40", "50"},
                {"music", "12:20", "40"},
                {"history", "14:00", "30"},
                {"computer", "12:30", "100"}
        });

        /*
            {"science", "12:40", "50"},{"music", "12:20", "40"},{"history", "14:00", "30"},{"computer", "12:30", "100"}
            {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}}
            {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}}
         */

        for(String subject : ans) {
            System.out.println(subject);
        }
    }

    static class Solution {
        public String[] solution(String[][] plans) {
            Queue<Task> tasks = new PriorityQueue<>((a, b) -> a.srtTime.isAfter(b.srtTime) ? 1 : -1);
            Stack<Task> pendingTasks = new Stack<>();
            List<Task> taskEndSeq = new ArrayList<>();

            for(String[] plan : plans) {
                tasks.add(new Task(plan));
            }

            LocalDateTime curTime;

            while(!tasks.isEmpty()) {
                Task curTask = tasks.poll();

                // 다음 과제가 있음 && 현재 과제가 진행중에 새로운 과제할 시간이됨
                if(!tasks.isEmpty() && tasks.peek().srtTime.isBefore(curTask.endTime)) {
                    Task nextTask = tasks.peek();
                    long timeGap = curTask.getTimeGap(nextTask);

                    curTask.spendTime -= timeGap;  // 과제 진행 시간 감소
                    pendingTasks.push(curTask);  // 뒤로 미루기
                    continue;
                }

                curTime = curTask.endTime;  // 현 과제 종료 하면서 끝나는 시간 기록
                taskEndSeq.add(curTask);  // 과제 종료

                // 임시 중지 과제 있음 && 다음 과제 있음 && 현재 시간이랑 다음 과제 시간 사이 차이가 있음
                if(!pendingTasks.isEmpty() && !tasks.isEmpty() && curTime.isBefore(tasks.peek().srtTime)) {
                    Task nextTask = tasks.peek();
                    Task pendingTask = pendingTasks.peek();
                    long timeGap = Duration.between(curTime, nextTask.srtTime).toMinutes();  // 현재 시간과 다음 시작 과제의 시간차이

                    while(timeGap >= 0) {  // 다음 시작 과제 시간전 여유 시간이 있을 때
                        if(pendingTask.spendTime <= timeGap) {  // 여유 시간으로 과제를 끝낼 수 있을 때
                            timeGap -= pendingTask.spendTime;  // 사용한 여유 시간 감소
                            curTime = pendingTasks.peek().endTime;  // 현재 시간 갱신
                            taskEndSeq.add(pendingTasks.pop());

                            if(pendingTasks.isEmpty()) break;

                            pendingTask = pendingTasks.peek();
                        } else {  // 여유 시간으로 끝낼 수 없을 때 시간만 감소
                            pendingTask.spendTime -= timeGap;
                            break;
                        }
                    }
                }
            }

            while(!pendingTasks.isEmpty()) {
                taskEndSeq.add(pendingTasks.pop());
            }

            return taskEndSeq.stream().map(Task::getSubject).toArray(String[]::new);
        }
    }

    static class Task {
        final String subject;
        LocalDateTime srtTime;
        LocalDateTime endTime;
        int spendTime;

        public Task(String[] info) {
            this.subject = info[0];
            this.spendTime = Integer.parseInt(info[2]);

            Integer[] time = Arrays.stream(info[1].split(":")).map(Integer::parseInt).toArray(Integer[]::new);

            this.srtTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(time[0], time[1]));
            this.endTime = this.srtTime.plusMinutes(this.spendTime);
        }

        public String getSubject() {
            return subject;
        }

        public long getTimeGap(Task task) {
            return Duration.between(this.srtTime, task.srtTime).toMinutes();  // (현)과제와 (새)과제 시간차
        }
    }
}