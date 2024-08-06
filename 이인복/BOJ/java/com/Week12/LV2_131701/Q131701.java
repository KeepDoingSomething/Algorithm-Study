package com.Week12.LV2_131701;

import java.util.HashSet;
import java.util.Set;

public class Q131701 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(new int[]{7,9,1,1,4})
        );
    }

    static class Solution {
        public int solution(int[] elements) {
            Set<Integer> set = new HashSet<>();

            for(int left = 0; left < elements.length; left++) {
                for(int gap = 0; gap < elements.length; gap++) {
                    int right = left + gap;

                    if(right > elements.length - 1) {
                        right = right - elements.length;
                    }

                    int sum = 0;
                    int test1 = left < right ? right : elements.length;
                    int test2 = left > right ? right : -1;

                    for(int i = left; i < test1; i++) {
                        sum += elements[i];
                    }

                    for(int i = 0; i < test2; i++) {
                        sum += elements[i];
                    }

                    set.add(sum);
                }
            }

            return set.size();
        }
    }
}
