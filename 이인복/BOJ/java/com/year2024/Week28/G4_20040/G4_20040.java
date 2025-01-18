/**
 * Author    : Lee In Bok
 * Date      : 2024.12.12(Thu)
 * Runtime   : 520 ms
 * Memory    : 151520 KB
 * Algorithm : Union & Find
 */

package com.year2024.Week28.G4_20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class G4_20040 {

    private static int[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = IntStream.range(0, N).toArray();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!union(a, b)) {
                System.out.println(i + 1);
                System.exit(0);
            }
        }

        System.out.println(0);
    }

    private static boolean union(int a, int b) {
        int rootOfA = find(a);
        int rootOfB = find(b);

        if(rootOfA == rootOfB) return false;

        graph[rootOfA] = rootOfB;

        return true;
    }

    private static int find(int node) {
        if(node == graph[node]) return node;

        return graph[node] = find(graph[node]);
    }
}