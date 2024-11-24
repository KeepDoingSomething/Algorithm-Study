/**
 * Author    : Lee In Bok
 * Date      : 2024.05.14(Tue)
 * Runtime   : 22468 KB
 * Memory    : 332 ms
 * Algorithm : Stack
 */

package com.Week01.S4_3986;

import com.self.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3986 implements Solution {

    @Override
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        for(int i = 0; i < N; i++) {
            Stack<Character> stack = new Stack<>();
            String word = br.readLine();

            for(int j = 0; j < word.length(); j++) {
                char letter = word.charAt(j);

                if(!stack.isEmpty() && stack.peek() == letter) {
                    stack.pop();
                    continue;
                }
                stack.push(letter);
            }

            ans += stack.isEmpty() ? 1 : 0;
        }
        System.out.print(ans);
    }
}