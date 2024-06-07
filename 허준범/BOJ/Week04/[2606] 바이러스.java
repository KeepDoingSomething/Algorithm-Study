/**
 * Author    : Heo jun boem
 * Date      : 2024.06.07(Fri)
 * Runtime   : 14284 KB
 * Memory    : 124 ms
 * Algorithm : BFS
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer> line[];
    static int count = 0;
    static boolean visit[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        line = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++)
            line[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            line[a].add(b);
            line[b].add(a);
        }

        visit = new boolean[n+1];
        bfs(1);

        System.out.println(count - 1);
    }

    static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visit[v] = true;
        count += 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int temp : line[cur]) {
                if (!visit[temp]) {
                    visit[temp] = true;
                    q.add(temp);
                    count++;
                }
            }
        }

    }
}
