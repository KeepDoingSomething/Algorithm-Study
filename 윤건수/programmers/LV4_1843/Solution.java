package programmers.LV4_1843;

public class Solution {

    public int solution(String arr[]) {
        String[] operations = new String[arr.length/2];
        int[] numbers = new int[arr.length/2 + 1];
        int[][] max = new int[arr.length/2 + 1][arr.length/2 + 1];
        int[][] min = new int[arr.length/2 + 1][arr.length/2 + 1];

        // 숫자, 기호 분리
        for(int i = 0; i < arr.length; i++){
            if(i%2 == 0){
                numbers[i/2] = Integer.parseInt(arr[i]);
            }else{
                operations[i/2] = arr[i];
            }
        }

        // DP 초기화
        for(int i = 0; i < numbers.length; i++){
            for(int j = 0; j < numbers.length; j++){
                if(i == j){
                    min[i][j] = numbers[i];
                    max[i][j] = numbers[i];
                }else{
                    min[i][j] = Integer.MAX_VALUE;
                    max[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        // j: 연산 횟수, i: 연산 시작위치, k: 중간 연산지점
        for(int j = 1; j <= operations.length; j++){
            for(int i = 0; i + j < numbers.length; i++){
                for(int k = 0; k < j; k++){
                    String oper = operations[i + k];
                    int start = i;
                    int end = i + j;
                    int mid = i + k;

                    int maxValue = 0;
                    int minValue = 0;
                    if(oper.equals("+")){
                        maxValue = max[start][mid] + max[mid + 1][end];
                        minValue = min[start][mid] + min[mid + 1][end];
                    } else {
                        maxValue = max[start][mid] - min[mid + 1][end];
                        minValue = min[start][mid] - max[mid + 1][end];
                    }

                    max[start][end] = Math.max(max[start][end], maxValue);
                    min[start][end] = Math.min(min[start][end], minValue);
                }
            }
        }

        int answer = max[0][numbers.length - 1];
        return answer;
    }

}