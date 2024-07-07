package programmers.LV2_12946;

import java.util.*;

public class Solution {

    ArrayList<int[]> result;

    public int[][] solution(int n) {
        result = new ArrayList<>();
        hanoi(1, 3, 2, n);

        int[][] answer = new int[result.size()][2];

        for(int i = 0; i < result.size(); i++){
            answer[i][0] = result.get(i)[0];
            answer[i][1] = result.get(i)[1];
        }

        return answer;
    }


    private void hanoi(int start, int end, int wait, int num){
        if(num == 1){
            result.add(new int[]{start, end});
        } else {
            hanoi(start, wait, end, num - 1);
            result.add(new int[]{start, end});
            hanoi(wait, end, start, num - 1);
        }
    }

}