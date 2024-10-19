/**
 * Author    : HojipKim
 * Date      : 2024.10.19(Sat)
 * Runtime   : 608 ms
 * Memory    : 145608 KB
 * Algorithm : 너비 우선 탐색, 이분 그래프, 깊이 우선 탐색, 그래프 이론, 그래프 탐색
 */

import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> list;
    public static int color[];
    public static boolean check = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            list = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= n; j++)
                list.add(new ArrayList<>());

            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.get(x).add(y);
                list.get(y).add(x);
            }

            color = new int[n + 1];
            check = false;

            for (int j = 1; j <= n; j++) {
                if(color[j] == 0){
                    bfs(j);
                }
                if(check) {
                    break;
                }
            }

            if(check) {
                bw.write("impossible\n");
            }
            else {
                bw.write("possible\n");
            }

        }
        bw.close();
    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1;

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : list.get(cur)){
                if(color[next] == 0){
                    q.add(next);
                    color[next] = -1 * color[cur];
                }else if(color[next] + color[cur] != 0){
                    check = true;
                    return;
                }
            }
        }
    }
}