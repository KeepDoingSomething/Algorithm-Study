/**
 * Author    : Lee In Bok
 * Date      : 2024.05.14(Tue)
 * Runtime   : 22468 KB
 * Memory    : 332 ms
 * Algorithm : Stack
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q3986 {
    public static void main(String[] args) throws IOException {
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
        System.out.println(ans);
    }
}