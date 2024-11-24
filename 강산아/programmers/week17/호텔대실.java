/**
 * Author    : Kang San Ah
 * Date      : 2024.09.20(Fri)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : Priority Queue
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 호텔대실 {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] bookingTime = new int[book_time.length][2];

        // 시간 비교를 위한 숫자형 변환
        for (int i = 0 ; i < book_time.length ; i++){
            int startTime = Integer.parseInt(book_time[i][0].replace(":",""));
            int endTime = Integer.parseInt(book_time[i][1].replace(":",""));

            // 청소 시간
            endTime += 10;

            // ex. 1970이면 -> 1810으로
            if (endTime%100 > 60){
                endTime += 40;
            }

            bookingTime[i][0] = startTime;
            bookingTime[i][1] = endTime;
        }

        Arrays.sort(bookingTime, (o1,o2) -> {
            return o1[0] - o2[0] ;
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        for (int i = 0 ; i < bookingTime.length; i++){

            if (pq.isEmpty()){
                pq.add(bookingTime[0]);
            }else{
                int[] peekTime = pq.peek();
                if (bookingTime[i][0] >= peekTime[1]){
                    pq.poll();
                }
                pq.add(bookingTime[i]);
            }
        }

        answer = pq.size();
        return answer;
    }
}
