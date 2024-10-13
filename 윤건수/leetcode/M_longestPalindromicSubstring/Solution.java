package leetcode.M_longestPalindromicSubstring;

import java.util.*;

public class Solution {
    public String longestPalindrome(String s) {

        Map<String, List<Integer>> strIdxs = new HashMap<>();
        String[] strs = s.split("");
        for(int i = 0; i < strs.length; i++){
            List<Integer> idxs = strIdxs.getOrDefault(strs[i], new ArrayList<Integer>());
            idxs.add(i);
            strIdxs.put(strs[i], idxs);
        }

        String result = s.substring(0, 1);
        int maxGap = 0;
        for(String key : strIdxs.keySet()){
            List<Integer> idxs = strIdxs.get(key);
            // System.out.println(idxs);
            for(int i = 0; i < idxs.size() - 1; i++){
                for(int j = i + 1; j < idxs.size(); j++){
                    int idx1 = idxs.get(i);
                    int idx2 = idxs.get(j);
                    int gap = idx2 - idx1;
                    String check = s.substring(idx1, idx2 + 1);
                    // System.out.println(check);
                    if(gap > maxGap && isPalindrome(check)){
                        maxGap = gap;
                        result = check;
                    }
                }
            }
        }

        return result;
    }

    public boolean isPalindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
}