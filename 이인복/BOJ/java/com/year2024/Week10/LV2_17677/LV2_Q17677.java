package com.year2024.Week10.LV2_17677;

import java.util.*;

public class LV2_Q17677 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        sol.solution("FRANCE", "french");
    }

    static class Solution {

        Set<String> allWords = new HashSet<>();

        public int solution(String str1, String str2) {
            int tmp1 = 0;
            int tmp2 = 0;
            Map<String, Integer> str1Map = getWordMap(str1.toUpperCase());
            Map<String, Integer> str2Map = getWordMap(str2.toUpperCase());

            for(String word : allWords) {
                tmp1 += Math.min(str1Map.getOrDefault(word, 0), str2Map.getOrDefault(word, 0));  // 공집합
                tmp2 += Math.max(str1Map.getOrDefault(word, 0), str2Map.getOrDefault(word, 0));  // 합집합
            }

            return tmp2 == 0 ? 65536 : (int)Math.floor(((double)tmp1 / tmp2) * 65536);
        }

        public Map<String, Integer> getWordMap(String str) {
            Map<String, Integer> map = new HashMap<>();

            for(int i = 0; i < str.length() - 1; i++) {
                String word = String.valueOf(str.charAt(i)) + String.valueOf(str.charAt(i + 1));

                if(word.matches("^[a-zA-Z]*$")) {
                    allWords.add(word);
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }

            return map;
        }
    }
}
