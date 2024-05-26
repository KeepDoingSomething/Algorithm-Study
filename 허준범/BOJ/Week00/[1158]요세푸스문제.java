/**
 * Author    : Heo jun boem
 * Date      : 2024.05.13(Mon)
 * Runtime   : 294772 KB
 * Memory    : 612 ms
 * Algorithm : Queue
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList<Integer> list = new LinkedList<Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        for (int i = 0; i < a; i++) {
            list.add(i+1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        while (!list.isEmpty()) {
            for (int i = 0; i < b-1; i++) {
                list.add(list.poll());
            }

            sb.append(list.poll());
            if(!list.isEmpty()){
                sb.append(", ");
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}
