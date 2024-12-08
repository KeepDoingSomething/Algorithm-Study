/**
 * Author    : Lee In Bok
 * Date      : 2024.11.18(Mon)
 * Runtime   : 256 ms
 * Memory    : 31672 KB
 * Algorithm : Prime Number, Two Pointer
 */

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

    // 투 포인터 로직으로 가능한 모든 해 찾음
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
                if(r >= primes.length) break;  // 인덱스 넘어가는 것 방지
                sum += primes[r];
            }
        }

        return ans;
    }

    public static int[] getPrimes() {
        boolean[] primes = new boolean[N + 1];  // false: 소수
        List<Integer> primeNumbers = new ArrayList<>();
        primes[0] = primes[1] = true;  // 0 과 1 은 소수 제외

        // 에라토스테네스의 체 시작
        for(int i = 2; i <= N; i++) {
            if(!primes[i]) {
                int notPrime = i + i;
                primeNumbers.add(i);  // 소수만 따로 저장

                while(notPrime <= N) {
                    primes[notPrime] = true;
                    notPrime += i;
                }
            }
        }

        return primeNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
