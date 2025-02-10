package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] graph = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            graph[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(graph);

        long min = Long.MAX_VALUE;
        long[] result = new long[3];
        for(int start = 0; start < N - 2; start++) {
            int mid = start + 1;
            int end = N - 1;
            while(mid < end) {
                long sum = graph[start] + graph[mid] + graph[end];

                if(Math.abs(sum) < min) {
                    result[0] = graph[start];
                    result[1] = graph[mid];
                    result[2] = graph[end];

                    min = Math.abs(sum);
                }

                if(sum < 0) {
                    mid++;
                } else if(sum == 0) {
                    break;
                } else {
                    end--;
                }
            }
        }

        for(int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
