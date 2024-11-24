package baekjoon.G3_1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int[] prime = initPrime(target);

        if(prime.length == 0){
            System.out.println(0);
            return;
        }

        int left = 0;
        int right = 0;
        int sum = prime[0];
        int answer = 0;

        while(left < prime.length){
            if(sum >= target){
                if(sum == target) answer++;
                sum -= prime[left];
                left++;
            }else{
                if(right + 1 < prime.length){
                    right++;
                    sum += prime[right];
                }else{
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    private static int[] initPrime(int target) {
        Boolean[] isPrime = new Boolean[target + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= target; i++) {
            if(!isPrime[i]) continue;
            for(int j = i + i; j <= target; j += i){
                isPrime[j] = false;
            }
        }

        return IntStream.rangeClosed(2, target).filter(idx -> isPrime[idx]).toArray();
    }


}
