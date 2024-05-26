// https://www.acmicpc.net/problem/2776
package baekjoon.S4_2776;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0){

            int keyCase = Integer.parseInt(br.readLine());
            Map<Integer, Boolean> remember = new HashMap<>();

            StringTokenizer rememberNum = new StringTokenizer(br.readLine());
            while(rememberNum.hasMoreTokens()){
                remember.put(Integer.parseInt(rememberNum.nextToken()), true);
            }

            int checkCase = Integer.parseInt(br.readLine());
            StringTokenizer checkNums = new StringTokenizer(br.readLine());
            while(checkNums.hasMoreTokens()){
                int checkNum = Integer.parseInt(checkNums.nextToken());
                boolean isRemember = remember.getOrDefault(checkNum, false);
                if(isRemember){
                    result.append(1).append(System.lineSeparator());
                }else{
                    result.append(0).append(System.lineSeparator());
                }
            }
        }

        System.out.println(result.toString().trim());
    }

}
