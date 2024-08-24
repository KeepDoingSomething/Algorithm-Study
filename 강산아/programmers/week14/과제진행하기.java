package programmers.week14;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 과제진행하기 {

    public String[] solution(String[][] plans) {
        LinkedList<Task> tasks = new LinkedList<>();
        for (String[] plan : plans) {
            tasks.offer(new Task(plan[0], convertToMinute(plan[1]), Integer.parseInt(plan[2])));
        }
        tasks.sort((t1, t2) -> t1.start - t2.start);

        Stack<Task> stopTasks = new Stack<>();
        List<String> endTasks = new ArrayList<>();
        Task now = tasks.poll();
        int time = now.start;
        while (!tasks.isEmpty()) {
            time += now.left;
            Task next = tasks.peek();

            if (time > next.start) {
                now.left = time - next.start;
                stopTasks.push(now);
            } else {
                endTasks.add(now.name);
                if (!stopTasks.empty()) {
                    now = stopTasks.pop();
                    continue;
                }
            }
            now = tasks.poll();
            time = now.start;
        }

        endTasks.add(now.name);
        while (!stopTasks.empty()) {
            endTasks.add(stopTasks.pop().name);
        }

        return endTasks.toArray(new String[0]);
    }

    private int convertToMinute(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    class Task {
        String name;
        int start;
        int left;

        Task(String name, int start, int left) {
            this.name = name;
            this.start = start;
            this.left = left;
        }
    }

}
