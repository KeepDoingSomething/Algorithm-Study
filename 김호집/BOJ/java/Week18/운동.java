/**
 * Author    : HojipKim
 * Date      : 2024.09.27(Fri)
 * Runtime   : 560 ms
 * Memory    : 59028 KB
 * Algorithm : 플로이드-워셜, 그래프 이론, 최단경로
 */

import java.io.*;
import java.util.*;

class Main{

    static int V;
    static long[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        distance = new long[V+1][V+1];

        // max value로 채워줌.
        for(long[] idx : distance){
            Arrays.fill(idx, Integer.MAX_VALUE);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance[a][b] = Math.min(distance[a][b], c);
        }

        // 플로이드 와샬 사용
        floydWarshall();

        long ans = Integer.MAX_VALUE;
        for (int i = 1; i < V+1; i++) {
            ans = Math.min(ans, distance[i][i]);
        }

        if (ans == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(ans));
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static void floydWarshall(){
        for (int i = 1; i < V+1; i++) {
            for (int j = 1; j < V+1; j++) {
                for (int k = 1; k < V+1; k++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                }
            }
        }
    }
}