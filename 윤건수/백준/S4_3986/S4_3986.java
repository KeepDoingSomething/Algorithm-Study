// https://www.acmicpc.net/problem/3986
package 백준.S4_3986;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class S4_3986 {

    public String solution(InputStream systemIn) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(systemIn));
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
        return String.valueOf(result);
    }

}
