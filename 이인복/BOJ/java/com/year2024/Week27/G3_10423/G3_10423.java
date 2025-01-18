/**
 * Author    : Lee In Bok
 * Date      : 2024.12.05(Thu)
 * Runtime   : 448 ms
 * Memory    : 46848 KB
 * Algorithm : Minimum Spanning Tree, Kruskal
 */

package com.year2024.Week27.G3_10423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class G3_10423 {

    static final int POWER_STATION = -1;
    static int N;
    static int M;
    static int K;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parent = IntStream.range(0, N + 1).toArray();  // 각 노드 번호 초기화
        Queue<Node> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < K; i++) {
            int nodeNum = Integer.parseInt(st.nextToken());

            parent[nodeNum] = -1;  // 발전소 위치 -1로 초기화(발전기 겹치지 않도록)
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            pq.add(new Node(
                    Integer.parseInt(st.nextToken()),  // 출발
                    Integer.parseInt(st.nextToken()),  // 도착
                    Integer.parseInt(st.nextToken()))  // 비용
            );
        }

        int ans = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int findSrt = find(node.srt);
            int findEnd = find(node.end);

            if(findSrt != findEnd) {
                ans += node.cost;
                union(findSrt, findEnd);
            }
        }

        System.out.println(ans);
    }

    public static int find(int nodeNum) {
        int parentNum = parent[nodeNum];

        // 발전소가 루트인 케이스
        if(parentNum == POWER_STATION) {
            return POWER_STATION;
        }

        // 아직 한 번도 부모 노드가 변경이 없던 케이스
        if(parentNum == nodeNum) {
            return parentNum;
        } else {
            /*
                부모가 -1 이라도 그 자식이 이미 다른 노드와 연결이 한 번 되었을 수 있어서 초기화 해줘야함
                ex) 6번 노드와 8번 노드가 이어진 케이스 (8번의 부모는 6)
                    노드 번호:     6 8
                    노드 부모 번호: 6 6

                이런 케이스에서 6번 노드가 발전소를 부모로 갖게 되면 -1이 반환 되는데
                6번 노드의 자식들의 부모가 초기화 되지 않는 문제가 있음
             */
            return parent[nodeNum] = find(parentNum);
        }
    }

    public static void union(int findSrt, int findEnd) {
        // if - else if 는 발전소인 경우 루트 노드이기 때문에 반대 노드의 부모가 된다
        if(findSrt == POWER_STATION) {
            parent[findEnd] = POWER_STATION;
        } else if(findEnd == POWER_STATION) {
            parent[findSrt] = POWER_STATION;
        } else {
            // srt 나 end 위치가 변경 되어도 상관은 없음
            parent[findEnd] = findSrt;
        }
    }

    static class Node implements Comparable<Node>{
        int srt;
        int end;
        int cost;

        public Node(int srt, int end, int cost) {
            this.srt = srt;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}