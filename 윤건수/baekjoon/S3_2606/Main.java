package baekjoon.S3_2606;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 바이러스 (Silver 3)
// https://www.acmicpc.net/problem/2606

public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] connects;
    static int resultCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computer = Integer.parseInt(br.readLine());
        int connectCnt = Integer.parseInt(br.readLine());
        visited = new boolean[computer + 1];
        connects = new ArrayList[computer + 1];
        resultCnt = 0;

        for(int i = 0; i <= computer; i++){
            connects[i] = new ArrayList<>();
        }

        StringTokenizer st;
        String line = null;

        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            int computer1 = Integer.parseInt(st.nextToken());
            int computer2 = Integer.parseInt(st.nextToken());

            connects[computer1].add(computer2);
            connects[computer2].add(computer1);
        }

        dfs(1);

        // 1 resultCnt 제거
        System.out.println(resultCnt - 1);
    }

    private static void dfs(int computer){
        if(visited[computer]) return;
        visited[computer] = true;
        resultCnt++;

        for(Integer connect : connects[computer]){
            dfs(connect);
        }
    }
}
