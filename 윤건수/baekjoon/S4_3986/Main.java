// https://www.acmicpc.net/problem/3986
package baekjoon.S4_3986;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0){
            String word = br.readLine();
            Stack<Character> check = new Stack<>();
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                if(check.isEmpty() || check.peek() != letter){
                    check.push(letter);
                }else{
                    check.pop();
                }
            }

            if(check.isEmpty()) result++;
        }

        System.out.println(result);
    }

}
