public class Lv2_250135 {
    public int calculate(int time){
        int hour = time;

        int min = time;

        return hour;
    }

    public int toSecond(int h, int m, int s){
        return h * 3600 + m * 60 + s;
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;

        int startTime = toSecond(h1, m1, s1);
        int endTime = toSecond(h2, m2, s2);

        return answer;
    }

    public static void main(String[] args) {
        Lv2_250135 sol = new Lv2_250135();

        System.out.println(sol.solution(0, 5, 30, 0, 7, 0));
    }
}
