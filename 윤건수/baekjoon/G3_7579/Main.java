package baekjoon.G3_7579;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] info = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] activeMemory = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int[] upMemory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long appCnt = info[0];
        long targetMemory = info[1];

        long[] dp = new long[Arrays.stream(upMemory).sum() + 1];

        for(int i = 0; i < appCnt; i++){
            int appUpMemory = upMemory[i];
            long appActiveMemory = activeMemory[i];

            for (int j = dp.length - 1; j - appUpMemory >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - appUpMemory] + appActiveMemory);
            }
        }

        int result = 0;
        for(int i = 0; i < dp.length; i++){
            if(dp[i] >= targetMemory){
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}
