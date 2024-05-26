/**
* Author    : Choi Jiho
* Date      : 2024.05.16(Thu)
* Runtime   : 2088 ms
* Memory    : 364692 KB
* Algorithm : HashMap
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            process();
        }
    }

    private static void process() throws IOException {
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map.put(Integer.parseInt(st.nextToken()), 1);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int a = Integer.parseInt(st.nextToken());
            sb.append(map.containsKey(a) ? 1 : 0);
            if (i < M - 1) sb.append('\n');
        }

        System.out.println(sb);
    }
}
