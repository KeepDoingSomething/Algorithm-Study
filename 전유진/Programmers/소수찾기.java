import java.util.HashSet;


/**
 * Author    : 전유진
 * Date      : 2024.06.03(월)
 * Algorithm : 완전탐색
 */

public class 소수찾기 {
    public static void main(String[] args) {
        String numbers = "011";
        solution(numbers);
    }

    static boolean[] visited;

    private static int solution(String numbers) {
        String[] arr = numbers.split("");
        visited = new boolean[arr.length];
        fac(arr,"");
        return res.size();
    }


    static HashSet<Integer> res =  new HashSet<Integer>();

    private static void fac(String[] numbers, String num) {
        //소수판결 후
        try {
            int number = Integer.parseInt(num);
            if(check(number)) res.add(number);

        } catch (Exception e) {

        }

        for (int i = 0; i < numbers.length; i++) {

            if (!visited[i]) {
                visited[i] = true;
                String num1 = num + numbers[i];
                fac(numbers, num1);
                visited[i] = false;
            }

        }
    }

    // 소수 판별
    private static boolean check(int number) {
        if(number <2 )return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i ==0) return false;
        }
        return true;
    }
}
