/**
 * Author    : Heo jun boem
 * Date      : 2024.05.15(Tue)
 * Runtime   : 22088 KB
 * Memory    : 332 ms
 * Algorithm : Stack
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for(int t = 0; t < tc; t++){

            String line = br.readLine();
            goodWordChk(line);

        }
        System.out.println(count);
    }

    public static void goodWordChk(String s){
        Stack<Character> stack = new Stack<>();

        if (s.length() % 2 != 0){
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (stack.peek() == s.charAt(i)) {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }

        if (stack.isEmpty()) {
            count++;
        }
    }
}
