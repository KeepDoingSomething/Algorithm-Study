/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.10(Tue)
 * Runtime   : 148ms
 * Memory    : 47224KB
 * Algorithm : BFS
 */

// >> 첫 번째 풀이 ( BFS )
// 너비 우선 탐색으로 풀이했습니다. 어떻게 하면 간결한 코드가 될 지 고민을 오래 했네요. (Solve Time : 0h 31m)
// 세 물통에 담겨 있는 물의 상태를 한 노드로 보고, 다른 물통으로 부으면서 상태가 달라질 때 이를 너비 우선 탐색할 수 있도록 큐를 사용했습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue; 
import java.util.LinkedList;

// 물통 상황 노드
class Node {
    int[] water;
    public Node(int a, int b, int c) {
        water = new int[]{a, b, c};
    }
}

class Main {
	static public void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물통 용량
        int[] limit = new int[3];
        for (int i = 0; i < 3; i++) limit[i] = Integer.parseInt(st.nextToken());

        // BFS Queue
		Queue<Node> q = new LinkedList<>();
        
        // BFS Visited
        int[][][] vis = new int[limit[0]+1][limit[1]+1][limit[2]+1];
        for (int i = 0; i < limit[0]+1; i++) {
            for (int j = 0; j < limit[1]+1; j++) Arrays.fill(vis[i][j], 0);
        }

        // 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득 차 있다.
        q.add(new Node(0, 0, limit[2]));
        vis[0][0][limit[2]] = 1;

        // 정답 리스트
        ArrayList<Integer> ans = new ArrayList<Integer>();

        while (!q.isEmpty()) {
            Node now = q.poll();

            // 첫 번째 물통이 비어 있을 때, 세 번째 물통에 담겨있을 수 있는 물의 양 등록
            if (now.water[0] == 0)
                ans.add(now.water[2]);

            int[] now_arr = new int[3];

            // (A, B, C) -> (A, B, C) 로 가득 물 붓기
            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    // 고른 두 물통이 같은 물통이거나, 부으려는 물이 비어있을 때는 건너뛰기
                    if (from == to || now.water[from] == 0) continue;
                    
                    // 물통 상태 now_arr 로 받아오기
                    for (int i = 0; i < 3; i++) now_arr[i] = now.water[i];

                    // to 물통이 가득 찰 때까지 물 붓기 / from 물통이 빌 때까지 물 붓기
                    int new_to = Math.min(limit[to], now_arr[to] + now_arr[from]);

                    // 새로운 물통 상태 만들기
                    now_arr[from] -= new_to - now_arr[to];
                    now_arr[to] = new_to;

                    // 방문한 상태가 아니라면, 더 탐색하도록 큐에 등록
                    if (vis[now_arr[0]][now_arr[1]][now_arr[2]] == 0) {
                        vis[now_arr[0]][now_arr[1]][now_arr[2]] = 1;
                        q.add(new Node(now_arr[0], now_arr[1], now_arr[2]));
                    }
                }
            }
        }

        // 정답 리스트 정렬 및 출력
        Collections.sort(ans);
        for (int e : ans) {
            System.out.print(e + " ");
        }
    }
}
