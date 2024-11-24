/**
 * Author    : Kang San Ah
 * Date      : 2024.11.19(Tue)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : DFS
 */

package programmers.week25;

public class 타겟넘버 {
    int answer = 0;

    public int solution(int[] numbers, int target) {
        dfs(0, numbers, target, 0);
        return answer;
    }

    private void dfs(int depth, int[] numbers, int target, int sum) {
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        dfs(depth + 1, numbers, target, sum + numbers[depth]);
        dfs(depth + 1, numbers, target, sum - numbers[depth]);
    }

    public static void main(String[] args) {
        타겟넘버 t1 = new 타겟넘버();
        int[] numbers = new int[5];
        for (int i = 0 ; i < numbers.length ; i++){
            numbers[i] = 1;
        }
        System.out.println(t1.solution(numbers,3));
    }
}
