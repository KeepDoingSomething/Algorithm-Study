/**
 * Author    : Lee In Bok
 * Date      : 2025.02.15(Sat)
 * Runtime   : 1308 ms
 * Memory    : 123728 KB
 * Algorithm : Priority Queue, Greedy
 */

package com.year2025.Week34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Jewellery> jewelleries = new PriorityQueue<>();
        Queue<Integer> bags = new PriorityQueue<>();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            jewelleries.add(new Jewellery(
               Integer.parseInt(st.nextToken()),
               Integer.parseInt(st.nextToken())
            ));
        }

        for(int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        Queue<Integer> tempBags = new PriorityQueue<>(Comparator.reverseOrder());
        long ans = 0;

        // ※ 가장 작은 가방부터 해당 가방에 들어갈 수 있는 보석중 가장 가치있는 보석을 넣는다.
        while(!bags.isEmpty()) {
            int bagSize = bags.poll();

            while(!jewelleries.isEmpty() && jewelleries.peek().weight <= bagSize) {
                tempBags.add(jewelleries.poll().value);
            }

            if(!tempBags.isEmpty()) {
                ans += tempBags.poll();
            }
        }

        System.out.println(ans);
    }

    static class Jewellery implements Comparable<Jewellery> {
        int weight;
        int value;

        public Jewellery(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewellery je) {
            return this.weight - je.weight;
        }
    }
}