package programmers.LV2_172927;

import java.util.*;

public class Solution {

    int[][] energy = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int[] picks;
    String[] minerals;
    HashMap<Integer, String> intToMineral;
    HashMap<String, Integer> mineralToInt;
    int answer;
    int maxPick;

    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
        for(int pickTry : picks){
            maxPick += pickTry * 5;
        }

        mineralToInt = new HashMap<String, Integer>();
        mineralToInt.put("diamond", 0);
        mineralToInt.put("iron", 1);
        mineralToInt.put("stone", 2);

        answer = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            if(picks[i] > 0){
                picks[i] --;
                dfs(picks, i, 0, 0);
                picks[i] ++;
            }
        }

        return answer;
    }

    private void dfs(int[] picks, int pick, int mineralIdx, int useEnergy){
        int repeat = 5;
        while(mineralIdx < minerals.length && repeat-- > 0){
            String type = minerals[mineralIdx];
            int mineral = mineralToInt.get(type);
            useEnergy += energy[pick][mineral];
            mineralIdx++;
        }

        if(mineralIdx == minerals.length || mineralIdx == maxPick){
            answer = Math.min(useEnergy, answer);
            return;
        }

        for(int i = 0; i < 3; i ++){
            if(picks[i] > 0){
                picks[i]--;
                dfs(picks, i, mineralIdx, useEnergy);
                picks[i]++;
            }
        }
    }
}