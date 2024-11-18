package com.Week25.G3_1644;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class G3_1644 {

    public static int N;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        if(N == 1) {
            System.out.println(0);
            return;
        }

        int[] primes = getPrimes();

        System.out.println(findComb(primes));
    }

    public static int findComb(int[] primes) {
        int l = 0;
        int r = 0;
        int ans = 0;
        int sum = primes[0];

        while(l < primes.length) {
            if(sum >= N) {
                if(sum == N) ans++;

                sum -= primes[l];
                l++;
            } else {
                r++;
                if(r >= primes.length) break;
                sum += primes[r];
            }
        }

        return ans;
    }

    public static int[] getPrimes() {
        boolean[] primes = new boolean[N + 1];
        List<Integer> primeNumbers = new ArrayList<>();
        primes[0] = primes[1] = true;

        for(int i = 2; i <= N; i++) {
            if(!primes[i]) {
                int notPrime = i + i;
                primeNumbers.add(i);

                while(notPrime <= N) {
                    primes[notPrime] = true;
                    notPrime += i;
                }
            }
        }

        return primeNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
