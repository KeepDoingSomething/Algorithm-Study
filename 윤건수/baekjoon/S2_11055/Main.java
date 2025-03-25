package baekjoon.S2_11055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[cnt];

        for(int i = 0; i < cnt; i++){
            int num1 = arr[i];
            int beforeSeqIdx = -1;
            int result = num1;
            for(int j = i - 1; j >= 0; j--){
                int num2 = arr[j];
                if(num2 < num1) {
                    result = Math.max(result, dp[j] + num1);
                }
            }

            dp[i] = result;
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());

    }

}
