/**
 * Author    : Lee In Bok
 * Date      : 2024.12.11(Wed)
 * Runtime   : 172 ms
 * Memory    : 26748 KB
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

            if(water.waters[0] == 0) treeSet.add(water.waters[2]);

            for(int from = 0; from < 3; from++) {
                for(int to = 0; to < 3; to++) {
                    if(from == to || water.waters[from] == 0) continue;

                    int[] newWaters = Arrays.copyOf(water.waters, 3);

                    // ex) A -> B 로 이동할 때  A + B 가 B 물통이 수용할 수 있는 최대를 넘었을 때
                    if(newWaters[from] + newWaters[to] >= capacity[to]) {
                        newWaters[from] = newWaters[from] - (capacity[to] - newWaters[to]);  // A = A - MAX_B - B (B 를 최대 수용 가능 까지 채울 수 있도록 A 에서 제거)
                        newWaters[to] = capacity[to];  // B = 최대 수용
                    } else {
                        newWaters[to] = newWaters[from] + newWaters[to];  // B = A + B
                        newWaters[from] = 0;  // A = 0
                    }

                    // 방문 처리 및 BFS 노드 추가
                    if(!visited[newWaters[0]][newWaters[1]][newWaters[2]]) {
                        visited[newWaters[0]][newWaters[1]][newWaters[2]] = true;
                        q.add(new Water(newWaters[0], newWaters[1], newWaters[2]));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int num : treeSet) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }

    static class Water {
        int [] waters;

        public Water(int a, int b, int c) {
            waters = new int[]{a, b, c};
        }
    }
}