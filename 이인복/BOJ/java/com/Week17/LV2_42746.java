/**
 * Author    : Lee In Bok
 * Date      : 2024.09.09(Tue)
 * Runtime   : 250.81ms
 * Memory    : 137MB
 * Algorithm : Sort
 */

package com.Week17;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LV2_42746 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(new int[]{3, 30, 34, 5, 9})
        );
    }

    static class Solution {
        public String solution(int[] numbers) {
            String[] strNums = Arrays.stream(numbers)
                                     .mapToObj(String::valueOf)
                                     .toArray(String[]::new);

            Arrays.sort(strNums, (a, b) -> {
                return Integer.compare(Integer.parseInt(b + a), Integer.parseInt(a + b));
            });

            String ans = Arrays.stream(strNums)
                               .collect(Collectors.joining());

            return ans.startsWith("0") ? "0" : ans;
        }
    }
}
