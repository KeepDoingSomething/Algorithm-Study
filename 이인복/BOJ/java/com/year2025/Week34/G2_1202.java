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
}

class Jewellery implements Comparable<Jewellery> {
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

/*
4 3
2 3
2 1
3 5
3 6
3
3
3
ans: 14

4 4
1 100
2 200
13 300
10 500
10
10
10
14
ans: 1100

9 5
4 5
4 9
4 10
8 55
14 20
9 15
8 55
8 5
11 54
10
5
4
15
20
ans: 183

3 3
20 12
0 20
16 16
17
14
7
ans: 36
 */