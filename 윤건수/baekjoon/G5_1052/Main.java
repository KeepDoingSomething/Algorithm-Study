package baekjoon.G5_1052;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int bottleNum = Integer.parseInt(st.nextToken());
        int bottleGoalNum = Integer.parseInt(st.nextToken());

        if (bottleNum <= bottleGoalNum) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int buyNum = 0;
        while (bottleNum-- > 0) {
            pq.add(1);
        }

        int last = 0;
        while (pq.size() > bottleGoalNum) {
            PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> o2 - o1);
            boolean isCompact = false;

            while(!pq.isEmpty()){
                int cur1 = pq.poll();
                if (pq.isEmpty() || cur1 != pq.peek()) {
                    last = cur1;
                    pq2.add(cur1);
                }else{
                    pq2.add(cur1 + pq.poll());
                    isCompact = true;
                }
            }

            if(!isCompact){
                pq2.add(last);
                buyNum += last;
            }
            pq = pq2;
        }

        System.out.println(buyNum);
    }

}