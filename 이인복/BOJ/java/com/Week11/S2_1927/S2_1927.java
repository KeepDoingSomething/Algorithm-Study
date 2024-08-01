package com.Week11.S2_1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class S2_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            int comm = Integer.parseInt(br.readLine());

            if(comm == 0) {
                if(pQueue.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(pQueue.poll());
                }
                sb.append(System.lineSeparator());
            } else {
                pQueue.add(comm);
            }
        }

        System.out.print(sb);
    }
}