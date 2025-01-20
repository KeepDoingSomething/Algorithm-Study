/**
 * Author    : Lee In Bok
 * Date      : 2024.12.22(Sun)
 * Runtime   : 108 ms
 * Memory    : 14096 KB
 * Algorithm : Topology Sort
 */

package com.year2024.Week29.G2_2637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G2_2637 {

    public static int N;
    public static int M;
    public static int[] parts;
    public static int[] indegree;
    public static List<Toy>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parts = new int[N + 1];
        indegree = new int[N + 1];
        graph = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int Z = Integer.parseInt(st.nextToken());

            graph[X].add(new Toy(X, Y, Z));
            indegree[Y] += 1;
        }

        bfs();
        printAnswer();
    }

    public static void printAnswer() {
        List<Integer> list = new ArrayList<>();  // 말단 노드 리스트
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++) {
            if(graph[i].isEmpty()) {
                list.add(i);
            }
        }

        for(int i : list) {
            sb.append(i).append(" ").append(parts[i]).append("\n");
        }

        System.out.println(sb);
    }

    public static void bfs() {
        Queue<Toy> q = new LinkedList<>(graph[N]);

        while(!q.isEmpty()) {
            Toy toy = q.poll();

            indegree[toy.to] -= 1;  // 진입 차수 한 개 제거

            // 한 번도 기록되지 않은 경우
            if(parts[toy.to] == 0 && parts[toy.from] == 0) {
                parts[toy.to] = parts[toy.from] + toy.cost;  // 현 노드 0 에서 초기화
            } else {
                parts[toy.to] += parts[toy.from] * toy.cost;  // 현재 노드에 값 누적
            }

            // 진입 차수가 모두 끊김
            if(indegree[toy.to] == 0) {
                q.addAll(graph[toy.to]);
            }
        }
    }

    static class Toy{
        int from;
        int to;
        int cost;

        public Toy(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}