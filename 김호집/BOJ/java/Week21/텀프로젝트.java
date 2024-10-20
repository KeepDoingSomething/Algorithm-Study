/**
 * Author    : HojipKim
 * Date      : 2024.10.19(Sat)
 * Runtime   : 1032 ms
 * Memory    : 303120 KB
 * Algorithm : 깊이 우선 탐색, 그래프 이론, 그래프 탐색
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int[] student;
    private static boolean[] isVisited;
    private static boolean[] isFinished;
    private static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            student = new int[n + 1];
            isVisited = new boolean[n + 1];
            isFinished = new boolean[n + 1];
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                student[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 1; j <= n; j++) {
                if (!isVisited[j]) {
                    dfs(j);
                }
            }

            bw.write((n - cnt) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int start) {
        isVisited[start] = true;

        int next = student[start];
        if (!isVisited[next]) {
            dfs(next);
        } else if (!isFinished[next]) {
            cnt++;
            for (int i = next; i != start; i = student[i]) {
                cnt++;
            }
        }

        isFinished[start] = true;
    }
}