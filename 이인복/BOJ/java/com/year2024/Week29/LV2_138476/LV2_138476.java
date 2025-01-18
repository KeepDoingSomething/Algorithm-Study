package com.year2024.Week29.LV2_138476;

import java.util.Arrays;
import java.util.*;
import java.util.stream.*;

public class LV2_138476 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int solution(int k, int[] tangerine) {
            int ans = 0;
            Queue<Long> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
            Map<Integer, Long> countMap = Arrays.stream(tangerine)
                                                .boxed()
                                                .collect(Collectors.groupingBy(
                                                        num -> num, Collectors.counting()
                                                ));

            pq.addAll(countMap.values());

            while(k > 0) {
                ans++;
                k -= pq.poll();
            }

            return ans;
        }
    }
}