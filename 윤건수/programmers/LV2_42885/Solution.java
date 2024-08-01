package programmers.LV2_42885;

import java.util.*;

public class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int lowIdx = 0;
        int highIdx = people.length - 1;

        while(highIdx>=lowIdx){
            if(people[highIdx] + people[lowIdx] <= limit){
                lowIdx++;
            }
            highIdx--;
            answer++;
        }
        return answer;
    }
}