/**
 * Author    : HojipKim
 * Date      : 2024.09.27(Fri)
 * Runtime   : 30.92 ms
 * Memory    : 90.3 MB
 * Algorithm : 우선순위큐
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime[][] book_localTime = new LocalTime[book_time.length][book_time[0].length];

        for(int i = 0; i < book_time.length; i++) {
            for(int j = 0; j < book_time[i].length; j++) {
                LocalTime localTime = LocalTime.parse(book_time[i][j], dateTimeFormatter);
                book_localTime[i][j] = localTime;
            }
        }

        Arrays.sort(book_localTime, (o1, o2) -> o1[0].compareTo(o2[0]));

        PriorityQueue<LocalTime> pq = new PriorityQueue<>();
        boolean isFindLine;
        for(int i = 0; i < book_localTime.length; i++) {
            isFindLine = false;
            LocalTime startTime = book_localTime[i][0];
            LocalTime endTime = book_localTime[i][1];

            if(!pq.isEmpty() && (pq.peek().plusMinutes(10).isBefore(startTime) || pq.peek().plusMinutes(10).equals(startTime))) {
                pq.poll();
                pq.add(endTime);
                isFindLine = true;
            }

            if(!isFindLine){
                pq.add(endTime);
            }

        }

        answer = pq.size();

        return answer;
    }
}