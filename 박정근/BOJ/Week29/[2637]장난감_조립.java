/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.20(Fri)
 * Runtime   : 120ms
 * Memory    : 15968KB
 * Algorithm : Dynamic Programming + Topology Sort
 */

// >> 첫 번째 풀이 ( 다이나믹 프로그래밍 + 위상 정렬 )
// 위상 정렬에 다이나믹 프로그래밍 기법을 녹여 풀이했습니다. (Solve Time : 0h 30m)
// Y -> X 를 간선으로써 그래프를 모델링한 뒤, 위상 정렬하면 inDegree가 0인 부품들이 기본 부품이 됩니다.
// 부품 최대 개수가 작으니, 2차원 배열에 특정 부품을 만들기 위한 부품 개수를 기록한다면, 다음 부품을 만들고 싶다면 K번 곱해서 넣어주면 됩니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

// Y -> (X, K) 간선 클래스
class Edge {
    int next, cnt;
    public Edge(int next, int cnt) {
        this.next = next;
        this.cnt = cnt;
    }
}

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<Edge>();

        // 각 정점에 들어오는 간선의 개수
        int[] inDegree = new int[n+1];
        Arrays.fill(inDegree, 0);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 그래프 구축. (Y -> X 간선이므로 X의 inDegree가 1 증가)
            inDegree[x] += 1;
            graph[y].add(new Edge(x, k));
        }

        // 특정 부품을 만들 때 필요한 기본 부품의 개수
        int[][] needs = new int[n+1][n+1];
        for (int i = 0; i < n+1; i++) Arrays.fill(needs[i], 0);

        // Topology Sort
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] != 0) continue;
            // inDegree가 0인 기본 부품을 큐에 넣는다. 기본 부품은 자기 자신을 1개 필요로 한다.
            q.add(i);
            needs[i][i] = 1;
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (Edge e : graph[now]) {
                // Topology Sort 진행. 
                // inDegree가 0이 되는 부품들은, 간선을 모두 탐색하여 0이 된 것이니, 필요한 기본 부품 개수가 다 채워진 것!
                inDegree[e.next] -= 1;
                if (inDegree[e.next] == 0) {
                    q.add(e.next);
                }

                // 다음 부품을 만들기 위해 필요한 부품들은, (현재 기본 부품) * K 개 이다.
                for (int i = 1; i <= n; i++) needs[e.next][i] += (needs[now][i]) * e.cnt;
            }
        }


        // 정답 출력 (오름차순)
        for (int i = 1; i <= n; i++) {
            if (needs[n][i] == 0) continue; 
            System.out.println(i + " " + needs[n][i]);
        }
    }
}
