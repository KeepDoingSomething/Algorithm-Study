package com.Week22.LV2_49993;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LV2_49993 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(
                        "CBD",
                        new String[] {"BACDE", "CBADF", "AECB", "BDA"}
                )
        );
    }

    static class Solution {
        public int solution(String skill, String[] skill_trees) {
            int ans = 0;
            Set<String> skills = Arrays.stream(skill.split("")).collect(Collectors.toSet());

            for(String tree : skill_trees) {
                String mainSkillTree = Arrays.stream(tree.split(""))
                                             .filter(skills::contains)
                                             .collect(Collectors.joining());

                if(skill.startsWith(mainSkillTree)) ans++;
            }

            return ans;
        }
    }
}
