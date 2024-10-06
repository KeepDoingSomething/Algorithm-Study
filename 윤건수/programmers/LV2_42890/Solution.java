package programmers.LV2_42890;

import java.util.*;

public class Solution {
    List<List<Integer>> uniqKey;
    String[][] relation;
    int columnCount;

    public int solution(String[][] relation) {

        this.uniqKey = new ArrayList<>();
        this.relation = relation;
        this.columnCount = relation[0].length;

        List<Integer> combination = new ArrayList<>();

        for(int i = 0; i < relation[0].length; i++){
            combination.add(i);
            dfs(combination);
            combination.clear();
        }

        int answer = 0;
        System.out.println(uniqKey);

        for(int i = 0; i < uniqKey.size(); i++){
            boolean isMinimal = true;
            List<Integer> key1 = uniqKey.get(i);
            for(int j = 0; j < uniqKey.size(); j++){
                if(i == j) continue;
                List<Integer> key2 = uniqKey.get(j);
                if(key1.containsAll(key2)) isMinimal = false;
            }

            if(isMinimal) answer++;
        }

        return answer;
    }

    public void dfs(List<Integer> combination){
        // System.out.println("combination: " + combination);
        if(checkUnique(combination)){
            uniqKey.add(new ArrayList<>(combination));
            return;
        }

        int last = combination.get(combination.size() - 1);
        for(int i = last + 1; i < columnCount; i++){
            combination.add(i);
            dfs(combination);
            combination.remove(combination.size() - 1);
        }
    }

    public boolean checkUnique(List<Integer> combination){

        Set<String> check = new HashSet<>();
        int rowCount = relation.length;
        // 알파벳 소문자와 숫자로만 이루어짐
        String spliter = "A";

        for(String[] row : relation){
            StringBuilder combinationValue = new StringBuilder();
            for(int column : combination){
                combinationValue.append(row[column]);
                combinationValue.append(spliter);
            }

            // System.out.println(combinationValue.toString());
            check.add(combinationValue.toString());
        }
        return rowCount == check.size();
    }


}