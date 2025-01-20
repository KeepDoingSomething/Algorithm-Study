/**
 * Author    : Lee In Bok
 * Date      : 2024.06.22(Sat)
 * Runtime   : 124872 KB
 * Memory    : 1560 ms
 * Algorithm : Heap
 */

package com.year2024.Week06.G2_1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G2_1202 {

    public static int M;
    public static int N;
    public static PriorityQueue<Gem> gems = new PriorityQueue<>((a, b) -> a.weight - b.weight);
    public static PriorityQueue<Integer> bags = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long ans = 0;

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            gems.add(new Gem(m, v));
        }

        for(int i = 0; i < N; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        PriorityQueue<Integer> valQ = new PriorityQueue<>(Comparator.reverseOrder());

        while(!bags.isEmpty()) {
            int bag = bags.poll();

            while(!gems.isEmpty() && bag >= gems.peek().weight) {
                valQ.add(gems.poll().value);
            }

            if(!valQ.isEmpty()) {
                ans += valQ.poll();
            }
        }

        System.out.println(ans);
    }

    static class Gem {
        int weight;  // 무게
        int value;  // 가치

        public Gem(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}