package baekjoon.S1_9081;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lineNum = Integer.parseInt(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            PriorityQueue<String> pq = new PriorityQueue<>();
            PriorityQueue<String> pq2 = new PriorityQueue<>();
            String result = line;
            String[] word = line.split("");
            int maxIdx = word.length - 1;
            pq.add(word[maxIdx]);

            for (int i = 0; i < maxIdx; i++) {
                int curIdx = maxIdx - i - 1;
                int compareIdx = maxIdx - i;
                String curStr = word[curIdx];
                String compareStr = word[compareIdx];
                // 4 3 2 1
                // 1 4 3 2
                if (curStr.compareTo(compareStr) < 0) {
                    result = line.substring(0, curIdx);

                    while (!pq.isEmpty()) {
                        String changeStr = pq.poll();
                        if(changeStr.compareTo(curStr) > 0){
                            result += changeStr;
                            pq.add(curStr);
                            while(!pq.isEmpty()){
                                pq2.add(pq.poll());
                            }
                            pq = pq2;
                            break;
                        }else{
                            pq2.add(changeStr);
                        }
                    }

                    while (!pq.isEmpty()) {
                        result += pq.poll();
                    }
                    break;
                }else{
                    pq.add(curStr);
                }
            }
            System.out.println(result);
        }
    }

}
