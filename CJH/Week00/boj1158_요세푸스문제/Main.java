/**
* Author    : Choi Jiho
* Date      : 2024.05.16(Thu)
* Runtime   : 524 ms
* Memory    : 294388 KB
* Algorithm : Queue
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    /**
     * 7 3
     * <3, 6, 2, 7, 5, 1, 4>
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Integer> q = new LinkedList<>();

        IntStream.rangeClosed(1, N)
                .forEach(q::add);

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int times = 0;
        while (!q.isEmpty()) {
            times++;
            Integer num = q.poll();
            if (times % K == 0) {
                sb.append(num).append(q.isEmpty() ? ">" : ", ");
            } else {
                q.offer(num);
            }
        }
        System.out.println(sb);
    }
}
