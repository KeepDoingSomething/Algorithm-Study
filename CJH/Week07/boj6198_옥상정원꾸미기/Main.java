package baekjoon.gold.gold5.boj6198_옥상정원꾸미기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;
    static Stack<Integer> stack = new Stack<>();
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());

            while (!stack.isEmpty()) {
                if (stack.peek() <= height) {
                    stack.pop();
                }
                else break;
            }

            ans += stack.size();
            stack.push(height);
        }

        System.out.println(ans);
    }
}
