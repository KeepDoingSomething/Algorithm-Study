package programmers.LV3_43238;

import java.util.*;

public class Solution {

    public long solution(int n, int[] times) {

        long min = 1;
        long maxAuditTime = Arrays.stream(times).max().orElseThrow(RuntimeException::new);
        long max = (n / times.length) * maxAuditTime;
        long answer = Long.MAX_VALUE;

        while(max >= min){
            long mid = (min + max) / 2;
            long auditPeople = 0;
            for(int time : times){
                auditPeople += mid / time;
            }

            if(auditPeople >= n){
                answer = Math.min(answer, mid);
                max = mid - 1;
            }else {
                min = mid + 1;
            }
        }

        return answer;
    }

}