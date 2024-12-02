/**
 * Author    : Kang San Ah
 * Date      : 2024.12.01(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : Two-Pointer
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 가장긴짝수연속한부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        String[] numbers = br.readLine().split(" ");
        int[] arr = new int[numbers.length];

        // 0 = 짝수, 1 = 홀수
        for (int i = 0 ; i < n; i++){
            int num = Integer.parseInt(numbers[i]);
            arr[i] = num % 2;
        }

        int l = 0, r = 0, cnt = 0, answer = 0;

        while (r <= n){
            if (cnt < k){
                if (arr[r] == 0) cnt ++;
                r++;
                answer = Math.max(answer, r-l-cnt);
            } else if (arr[r] == 1) {
                r++;
                answer = Math.max(answer, r-l-cnt);
            }else{
                if (arr[l] == 0)cnt --;
                l++;
            }
        }
        System.out.println(answer);
    }
}
