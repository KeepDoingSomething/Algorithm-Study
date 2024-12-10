/**
 * Author    : Lee In Bok
 * Date      : 2024.12.11(Wed)
 * Runtime   : 176 ms
 * Memory    : 26724 KB
 * Algorithm : BFS
 */

package com.Week28.G4_2251;

import java.util.*;

public class G4_2251 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] capacity = Arrays.stream(sc.nextLine().split(" "))
                               .mapToInt(Integer::parseInt)
                               .toArray();
        Queue<Water> q = new LinkedList<>();
        boolean[][][] visited = new boolean[capacity[0] + 1][capacity[1] + 1][capacity[2] + 1];
        Set<Integer> treeSet = new TreeSet<>();

        q.add(new Water(0, 0, capacity[2]));

        while(!q.isEmpty()) {
            Water water = q.poll();

            if(!visited[water.a][water.b][water.c]) {
                if(water.a == 0) {  // A 가 비어 있을 때 C 의 상태 물의 양 저장
                    treeSet.add(water.c);
                }

                visited[water.a][water.b][water.c] = true;  // 방문 처리

                // A -> B
                if(water.a + water.b >= capacity[1]) {
                    q.add(new Water(water.a - (capacity[1] - water.b), capacity[1], water.c));
                } else {
                    q.add(new Water(0, water.a + water.b, water.c));
                }

                // A - > C
                if(water.a + water.c >= capacity[2]) {
                    q.add(new Water(water.a - (capacity[2] - water.c), water.b, capacity[2]));
                } else {
                    q.add(new Water(0, water.b, water.a + water.c));
                }

                // B -> A
                if(water.b + water.a >= capacity[0]) {
                    q.add(new Water(capacity[0], water.b - (capacity[0] - water.a), water.c));
                } else {
                    q.add(new Water(water.a + water.b, 0, water.c));
                }

                // B -> C
                if(water.b + water.c >= capacity[2]) {
                    q.add(new Water(water.a, water.b - (capacity[2] - water.c), capacity[2]));
                } else {
                    q.add(new Water(water.a, 0, water.b + water.c));
                }

                // C -> A
                if(water.a + water.c >= capacity[0]) {
                    q.add(new Water(capacity[0], water.b, water.c - (capacity[0] - water.a)));
                } else {
                    q.add(new Water(water.a + water.c, water.b, 0));
                }

                // C -> B
                if(water.b + water.c >= capacity[1]) {
                    q.add(new Water(water.a, capacity[1], water.c - (capacity[1] - water.b)));
                } else {
                    q.add(new Water(water.a, water.b + water.c, 0));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int num : treeSet) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }

    static class Water {
        int a;
        int b;
        int c;

        public Water(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}