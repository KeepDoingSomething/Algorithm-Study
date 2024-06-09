import java.util.HashSet;

public class Lv2_42839 {
    static int answer, len;
    static String[] strArr;
    static boolean[] visit;
    static HashSet<Integer> set;

    public static boolean checkPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; (i * i) <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void DFS(String current, int depth) {
        if (!current.equals("")) {
            set.add(Integer.parseInt(current));
        }

        if (depth == len) {
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visit[i]) {
                visit[i] = true;
                DFS(current + strArr[i], depth + 1);
                visit[i] = false;
            }
        }
    }

    public int solution(String numbers) {
        answer = 0;

        len = numbers.length();
        visit = new boolean[len];
        strArr = numbers.split("");
        set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            visit[i] = true;
            DFS(strArr[i], 1);
            visit[i] = false;
        }

        for (int num : set) {
            if (checkPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Lv2_42839 sol = new Lv2_42839();
        System.out.println(sol.solution("1231"));
    }
}
