package programmers.LV2_131701;

import java.util.*;
public class Solution {
    static Set<Integer> set = new HashSet();
    static int[] elements;
    public int solution(int[] elements) {
        this.elements = elements;
        int combinationWays = elements.length;
        for(int i=0; i<elements.length; i++){
            setNumberByCombinationWays(combinationWays--);
        }
        return set.size();
    }

    public static void setNumberByCombinationWays(int combinationWays){
        for(int i=0; i<elements.length; i++){
            int partSum = 0;
            int partNum = combinationWays;
            while(partNum-- > 0){
                partSum += elements[(i+partNum)%elements.length];
            }
            set.add(partSum);
        }
    }
}