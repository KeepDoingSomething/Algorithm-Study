/**
 * Author    : Lee In Bok
 * Date      : 2024.05.14(Tue)
 * Runtime   : 420232 KB
 * Memory    : 2036 ms
 * Algorithm : Hash
 */

package com.year2024.Week01.S4_2776;

import com.self.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Q2776 implements Solution {

    @Override
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            Set<Integer> note1 = Arrays.stream(br.readLine().split(" "))
                                       .mapToInt(Integer::parseInt)
                                       .boxed()
                                       .collect(Collectors.toSet());
            int M = Integer.parseInt(br.readLine());

            Arrays.stream(br.readLine().split(" "))
                  .mapToInt(Integer::parseInt)
                  .forEach(e -> sb.append(note1.contains(e) ? 1 : 0).append('\n'));
        }

        System.out.print(sb.toString());
    }
}