/**
 *
 *  *   Author    : 전유진
 *  *    Date      : 2024.11.24(일)
 *  *     Algorithm : 팩토리얼
 *
 */
public class 타켓넘버 {
    public static void main(String[] args) {
        int[] numbers = {4, 1, 2, 1};
        int target = 4;
        solution(numbers, target);
    }

    private static int solution(int[] numbers, int target) {

        fac(numbers, target, 0, 0);
        return answer;

    }
    static int answer = 0;

    private static void fac(int[] numbers, int target, int depth, int res) {
        if (numbers.length == depth) {
            if(res == target) answer++;
            return;
        }

        fac(numbers, target, depth + 1, res + numbers[depth]);
        fac(numbers, target, depth + 1, res - numbers[depth]);

    }
}
