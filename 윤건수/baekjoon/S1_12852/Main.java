package baekjoon.S1_12852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int MAX = n + 1;
        Deque<Integer>[] dp = new ArrayDeque[MAX];
        for(int i = 1; i < MAX; i++) {
            dp[i] = new ArrayDeque<>();
        }
        dp[1].push(1);

        for(int i = 1; i < MAX; i++){
            Deque saved = dp[i];
            int[] next = {i * 3, i * 2, i + 1};
            for (int nextValue : next) {
                if(nextValue < MAX && (dp[nextValue].isEmpty() || dp[nextValue].size() > saved.size() + 1)){
                    Deque<Integer> nextSaved = dp[nextValue];
                    nextSaved.clear();
                    nextSaved.addAll(saved);
                    nextSaved.push(nextValue);
                }
            }
        }

        Deque<Integer> stack = dp[n];
        int tryCnt = stack.size() - 1;
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(tryCnt);
        System.out.println(sb.toString().trim());
    }

}
