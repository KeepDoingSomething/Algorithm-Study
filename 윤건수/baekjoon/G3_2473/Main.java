package baekjoon.G3_2473;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        long[] values = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(values);

        int answerLeft = 0;
        int answerRight = values.length - 1;
        int answerMid = answerLeft + 1;

        for(int mid = 1; mid < values.length; mid++){
            int left = 0;
            int right = values.length - 1;
            while (left < mid && right > mid) {
                long sum = values[left] + values[mid] + values[right];
                long sumAbs = Math.abs(sum);

                if(sumAbs < Math.abs(values[answerLeft] + values[answerMid] + values[answerRight])){
                    answerLeft = left;
                    answerMid = mid;
                    answerRight = right;
                }

                if(sum > 0){
                    right--;
                }else{
                    left++;
                }
            }

        }

        System.out.println(values[answerLeft] + " " + values[answerMid] + " " + values[answerRight]);
    }

}
