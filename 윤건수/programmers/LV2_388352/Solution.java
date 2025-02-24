package programmers.LV2_388352;

public class Solution {

    int[] numbers;
    int answerLength;
    Checker checker;
    int answer;

    public int solution(int n, int[][] q, int[] ans) {
        answerLength = q[0].length;
        this.checker = new Checker(q, ans);
        answer = 0;
        numbers = new int[n];

        for(int i = 0; i < n; i++){
            numbers[i] = i + 1;
        }

        int[] made = new int[answerLength];
        dfs(made, -1, -1);

        return answer;
    }

    private void dfs(int[] made, int before, int depth){

        if(depth + 1 == this.answerLength){
            if(this.checker.isPass(made)){
                answer++;
            }
            return;
        }

        depth++;
        for(int i = before + 1; i < numbers.length; i++){
            made[depth] = numbers[i];
            dfs(made, i, depth);
        }
    }

    private class Checker{
        int[][] caseNumbers;
        int[] caseCounts;

        Checker(int[][] caseNumbers, int[] caseCounts){
            this.caseNumbers = caseNumbers;
            this.caseCounts = caseCounts;
        }

        boolean isPass(int[] arr){

            for(int i = 0; i < caseNumbers.length; i++){
                int[] caseNumber = caseNumbers[i];
                int caseCount = caseCounts[i];
                int includeCnt = 0;
                for(int j = 0; j < arr.length; j++){
                    int arrNum =  arr[j];
                    for(int checkCaseNum : caseNumber){
                        if(arrNum == checkCaseNum) includeCnt++;
                    }
                }

                if(includeCnt != caseCount) return false;
            }

            return true;
        }
    }

}