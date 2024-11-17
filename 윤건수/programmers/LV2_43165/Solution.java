package programmers.LV2_43165;

public class Solution {

    int numbersLength;
    int[] numbers;
    int target;
    int answer = 0;
    Operator[] operators;

    public int solution(int[] numbers, int target) {
        this.numbersLength = numbers.length;
        this.numbers = numbers;
        this.target = target;
        operators = new Operator[]{
                (a, b) -> (a + b),
                (a, b) -> (a - b)
        };

        for (Operator oper : operators) {
            dfs(0, 0, oper);
        }

        return answer;
    }

    public void dfs(int value, int depth, Operator curOperator){
        value = curOperator.execute(value, this.numbers[depth]);
        depth++;

        if(depth == this.numbersLength){
            if(value == this.target) this.answer++;;
            return;
        }

        for (Operator nextOperator : operators) {
            dfs(value, depth, nextOperator);
        }
    }

    @FunctionalInterface
    public interface Operator{
        int execute(int a, int b);
    }
}