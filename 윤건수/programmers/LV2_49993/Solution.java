package programmers.LV2_49993;

public class Solution {
    public int solution(String skill, String[] skill_trees) {

        String[] skillTree = skill.split("");
        int answer = 0;

        for(String tree : skill_trees){
            String[] userTree = tree.split("");
            int treeIdx = 0;
            int userIdx = 0;

            while(userIdx < tree.length() && treeIdx < skill.length()){
                if(skill.contains(userTree[userIdx]) && userTree[userIdx].equals(skillTree[treeIdx])){
                    userIdx++;
                    treeIdx++;
                }else if (skill.contains(userTree[userIdx]) && !userTree[userIdx].equals(skillTree[treeIdx])) {
                    break;
                }else{
                    userIdx++;
                }
            }

            if(userIdx >= tree.length() || treeIdx >= skill.length()){
                answer++;
            }

        }

        return answer;
    }

}