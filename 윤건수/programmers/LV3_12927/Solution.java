package programmers.LV3_12927;

import java.util.*;

public class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int work: works){
            pq.add(work);
        }

        while(n-- > 0){
            int work = pq.poll();
            pq.add(work == 0 ? 0 : work - 1);
        }

        long answer = 0;
        while(!pq.isEmpty()){
            long work = (long)pq.poll();
            answer += work * work;
        }

        return answer;
    }
}