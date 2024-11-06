import java.util.Scanner;

public class 숫자의표현 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int solution = solution(n);
        System.out.println("solution = " + solution);
    }

    public static int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int sum = 0;

            for (int j = i; j <= n; j++) {
                sum += j;

                if (sum == n) {
                    answer++;
                    break;
                }

                if (sum > n) break;
            }
        }

        return answer;
    }


}
