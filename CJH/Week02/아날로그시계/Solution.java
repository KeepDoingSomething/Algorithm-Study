package programmers.level2.아날로그시계;

import java.util.List;

public class Solution {

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        int startTime = new Time(h1 * 3600 + m1 * 60 + s1).toSeconds();
        int endTime = new Time(h2 * 3600 + m2 * 60 + s2).toSeconds();

        // 시작시간이 0시 또는 12시라면 한번 겹침
        if (startTime == 0 || startTime == (3600 * 12)) {
            answer++;
        }

        for (int i = startTime; i < endTime; i++) {
            List<Double> cur = new Time(i).getDegree();
            List<Double> next = new Time(i + 1).getDegree();

            boolean minuteMatch = isMinuteMatch(cur, next);
            boolean hourMatch = isHourMatch(cur, next);

            if (minuteMatch && hourMatch) {
                // 분침과 시침이 일치하면 +1
                if (next.get(1).equals(next.get(0))) {
                    answer++;
                } else {
                    answer += 2;
                }
            } else if (minuteMatch || hourMatch) {
                answer++;
            }
        }


        return answer;
    }

    private boolean isHourMatch(List<Double> cur, List<Double> next) {
        if (cur.get(2) == 354 && cur.get(0) > 354) {
            return true;
        }
        return cur.get(0) > cur.get(2) && next.get(0) <= next.get(2);
    }

    private boolean isMinuteMatch(List<Double> cur, List<Double> next) {
        if (cur.get(2) == 354 && cur.get(1) > 354) {
            return true;
        }
        return cur.get(1) > cur.get(2) && next.get(1) <= next.get(2);
    }

}

class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int seconds) {
        this.hour = seconds / 3600;
        this.minute = (seconds % 3600) / 60;
        this.second = (seconds % 3600) % 60;
    }

    public List<Double> getDegree() {
        double hourAngle = (hour % 12) * 30 + (minute * 0.5) + (second * (0.5 / 60));
        double minuteAngle = minute * 6 + (second * 0.1);
        double secondAngle = second * 6;

        return List.of(hourAngle, minuteAngle, secondAngle);
    }

    public int toSeconds() {
        return hour * 3600 + minute * 60 + second;
    }


}
