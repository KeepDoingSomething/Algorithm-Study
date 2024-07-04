/**
 * Author    : Lee In Bok
 * Date      : 2024.07.04(Mon)
 * Runtime   : 24448 KB
 * Memory    : 300 ms
 * Algorithm : Stack
 */

package com.Week08.Q6198;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class G5_6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            while(!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }

            stack.push(num);
            ans += stack.size() - 1;
        }

        System.out.println(ans);
    }
}