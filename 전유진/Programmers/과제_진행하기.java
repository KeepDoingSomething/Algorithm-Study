import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 *  *   Author    : 전유진
 *  *    Date      : 2024.08.24(토)
 *  *     Algorithm : 우선순위 큐
 *  fail
 */
public class 과제_진행하기 {
    public static void main(String[] args) {
        String[][] plays = {
                {"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}
        };

        System.out.println(Arrays.toString(solution(plays)));
        ;

    }
    static class Assign {
        String name;
        int start;
        int playtime;

        public Assign(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }

    private static String[] solution(String[][] plays) {
        LinkedList<String> res = new LinkedList<>();
        Deque<Assign> remainTask = new LinkedList<>();
        PriorityQueue<Assign> queue = new PriorityQueue<>((o1,o2)->{
            return  o1.start-o2.start;
        });
        for (int i = 0; i < plays.length; i++) {
            String[] play = plays[i];
            String name = play[0];
            String[] time = play[1].split(":");
            int minute = (Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]); // 분으로 변경
            int playtime = Integer.parseInt(play[2]);
            queue.add(new Assign(name, minute, playtime));
        }

        int currentTime = queue.peek().start; // 현재 시간을 초기 과제의 시작 시간으로 설정

        while (!queue.isEmpty()) {
            Assign currentTask = queue.poll(); //현재 과제

            if (queue.isEmpty()) {
                // 남은 과제가 없으면 현재 과제와 대기 과제들을 모두 처리
                res.add(currentTask.name);
                while (!remainTask.isEmpty()) {
                    res.add(remainTask.poll().name);
                }
                break;
            }
            Assign nextTask = queue.peek(); // 다음 과제
            int remainTime = nextTask.start - currentTask.start; // 두 시간까지의 차이
            if (remainTime >= currentTask.playtime) { // 다음 시작 시간까지  현재 과제를 끝낸다면
                res.add(currentTask.name);
                System.out.println(res);
                if (!remainTask.isEmpty()) { // 남겨진 과제가 있다면
                    Assign poll = remainTask.poll();
                    int remain = remainTime - poll.playtime;
                    if (remain > 0) { // 남은 시간에 끝낼 수 없다면
                        remainTask.addFirst(new Assign(currentTask.name,poll.start, remain));
                    } else { // 끝낼 수 있다면
                        remainTask.poll();
                    }
                }

            } else {// 다음 시작 시간까지  현재 과제를 끝낼 수  없다면

                int remainMinute = nextTask.start - currentTask.start;
                int remainPlayTime = currentTask.playtime - nextTask.start;
                remainTask.add(new Assign(currentTask.name,remainMinute, remainPlayTime)); //멈춘 과제에 넣는다.
            }
        }


        String[] array = res.stream().toArray(String[]::new);
        return array;
    }
}
