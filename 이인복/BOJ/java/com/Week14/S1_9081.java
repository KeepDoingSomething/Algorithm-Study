package com.Week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1_9081 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            String word = br.readLine();
            String[] wordArr = word.split("");
            Queue<String> pQueue = backtracking(wordArr, new HashSet<>(), new boolean[wordArr.length], new StringBuilder());

            while(!pQueue.isEmpty()) {
                if(pQueue.poll().equals(word)) {
                    String ansWord = pQueue.poll();

                    sb.append(Objects.isNull(ansWord) ? word : ansWord).append(System.lineSeparator());
                }
            }
        }

        System.out.println(sb);
    }

    public static PriorityQueue<String> backtracking(String[] word, Set<String> wordSet, boolean[] visited, StringBuilder sb) {
        if(sb.length() == word.length) {
            wordSet.add(sb.toString());
        }

        for(int i = 0; i < word.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                sb.append(word[i]);
                backtracking(word, wordSet, visited, sb);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }

        return new PriorityQueue<>(wordSet);
    }
}