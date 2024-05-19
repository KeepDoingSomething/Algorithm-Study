/**
 * Author    : Lee In Bok
 * Date      : 2024.05.14(Tue)
 * Runtime   : 295080 KB
 * Memory    : 604 ms
 * Algorithm : Queue
 */

package com.Week00.S4_1158;

import com.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q1158 implements Solution {
    @Override
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder().append("<");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList(IntStream.rangeClosed(1, N).boxed().collect(Collectors.toList()));

        while(!queue.isEmpty()) {
            for(int i = 1; i < K; i++){
                queue.add(queue.poll());
            }
            sb.append(queue.poll()).append(", ");
        }

        System.out.println(sb.delete(sb.length() - 2, sb.length()).append(">").toString());
    }
}