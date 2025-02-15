/**
 * Author    : Lee In Bok
 * Date      : 2025.02.15(Sat)
 * Runtime   : 176 ms
 * Memory    : 18740 KB
 * Algorithm : Union & Find
 */

package com.year2025.Week34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class G4_1976 {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = IntStream.range(0, N).toArray();

        for(int srt = 0; srt < N; srt++) {
            int[] srtToEnd = Arrays.stream(br.readLine().split(" "))
                                   .mapToInt(Integer::parseInt)
                                   .toArray();

            for(int end = 0; end < N; end++) {
                if(srtToEnd[end] == 0) continue;

                union(srt, end);
            }
        }

        long count = Arrays.stream(br.readLine().split(" "))
                           .mapToInt(Integer::parseInt)
                           .map(e -> find(e - 1))
                           .distinct()
                           .count();

        System.out.println(count == 1 ? "YES" : "NO");
    }

    public static void union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);

        if(rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

    public static int find(int node) {
        if(node == parent[node]) return node;

        return parent[node] = find(parent[node]);
    }
}