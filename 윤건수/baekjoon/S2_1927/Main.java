package baekjoon.S2_1927;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

// 최소힙 (Silver 2)
// https://www.acmicpc.net/problem/1927

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int lineNum = Integer.parseInt(br.readLine());
        String line = "";
        while ((line = br.readLine()) != null) {
            int num = Integer.parseInt(line);
            if (num == 0) {
                String result = heap.isEmpty() ? "0" : heap.poll().toString();
                bw.write(result);
                bw.newLine();
            } else {
                heap.add(num);
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

}