package baekjoon.G5_5557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numsCnt = br.readLine();
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[][] caseCnt = new long[nums.length - 1][21];
        caseCnt[0][nums[0]] = 1;
        for (int i = 1; i < nums.length - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                long beforeCaseCnt = caseCnt[i - 1][j];
                if(beforeCaseCnt > 0){
                    int plusResult = j + nums[i];
                    int minusResult = j - nums[i];
                    if(plusResult >= 0 && plusResult <= 20) {
                        caseCnt[i][plusResult] += beforeCaseCnt;
                    }

                    if(minusResult >= 0 && minusResult <= 20){
                        caseCnt[i][minusResult] += beforeCaseCnt;
                    }
                }
            }
        }

        int last =  caseCnt.length - 1;
        int target = nums[nums.length - 1];
        System.out.println(caseCnt[last][target]);

    }

}
