package programmers.LV2_340212;

import java.util.*;

public class Solution {

    int[] times;
    int[] diffs;

    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;

        int low = 0;
        int high = 0;
        for(int diff: diffs){
            high = Math.max(high, diff);
        }

        int answer = 1;
        while(high >= low){
            int mid = (low + high) / 2;
            long solveTime = calcTime(mid);
            if(solveTime <= limit){
                answer = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return answer;
    }

    public long calcTime(int level){
        long result = 0;
        for(int i = 0; i < diffs.length; i++){
            int diff = diffs[i];
            int timeCur = times[i];
            long timePrev = i == 0 ? 0 : times[i - 1];
            int retry = Math.max(diff - level, 0);
            result += (retry * (timePrev + timeCur)) + timeCur;
        }
        return result;
    }
}