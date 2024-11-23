/**
 * Author    : Kang San Ah
 * Date      : 2024.11.23(Sat)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 에라토스테네스 체, Two-pointer
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 소수의연속합 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 에라토스테네스 (소수 구하기)
        int[] prime = new int[N + 1];
        ArrayList<Integer> primeList = new ArrayList<Integer>();

        for (int i = 2; i <= N ; i++){
            if (prime[i] == 0) {
                primeList.add(i);
                for (int j = i; j <= N; j = j + i) {
                    prime[j] = 1;
                }
            }
        }

        // 투 포인터 알고리즘
        int sum = 0, start = 0, end = 0, answer = 0;
        while(true){
            if (sum >= N) sum -= primeList.get(start ++);
            else if(end == primeList.size()) break;
            else sum += primeList.get(end++);

            if (sum == N ) answer ++;
        }

        System.out.println(answer);

    }
}
