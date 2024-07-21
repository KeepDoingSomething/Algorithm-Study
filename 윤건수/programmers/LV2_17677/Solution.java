package programmers.LV2_17677;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    Set<String> wordSet = new HashSet();

    public int solution(String str1, String str2) {
        Map<String, Integer> wordMap1 = convertArray(str1);
        Map<String, Integer> wordMap2 = convertArray(str2);

        int sumSetValue = 0;
        int crossSetValue = 0;

        for(String word: wordSet){
            sumSetValue += Math.max(wordMap1.getOrDefault(word, 0), wordMap2.getOrDefault(word, 0));
            crossSetValue += Math.min(wordMap1.getOrDefault(word, 0), wordMap2.getOrDefault(word, 0));
        }

        return sumSetValue == 0 ? 65536 : (int)Math.floor((crossSetValue/(double)sumSetValue) * 65536);
    }

    public Map<String, Integer> convertArray(String str){
        Map<String, Integer> wordMap = new HashMap();

        for(int i=0; i < str.length() - 1; i++){
            String word = Character.toString(str.charAt(i)) + Character.toString(str.charAt(i+1));
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if(word.length() != 2) continue;
            wordSet.add(word);
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        return wordMap;
    }
}