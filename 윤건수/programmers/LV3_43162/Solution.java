package programmers.LV3_43162;

import java.util.*;

public class Solution {

    int[] parent;

    public int solution(int n, int[][] computers) {
        parent = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        for(int i = 0; i < computers.length; i++){
            for(int j = 0; j < computers[i].length; j++){
                if(computers[i][j] == 1){
                    union(i, j);
                }
            }
        }

        for(int i = 0; i < n; i ++){
            union(parent[i], i);
        }

        Set<Integer> answerSet = new HashSet<>();
        Arrays.stream(parent).forEach(answerSet::add);

        return answerSet.size();
    }

    public void union(int com1, int com2){

        int parent1 = find(com1);
        int parent2 = find(com2);

        if(parent1 != parent2) parent[parent2] = parent1;
    }

    public int find(int computer){
        if(computer == parent[computer]){
            return computer;
        }else {
            parent[computer] = find(parent[computer]);
            return parent[computer];
        }
    }


}