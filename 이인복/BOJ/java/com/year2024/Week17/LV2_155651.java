/**
 * Author    : Lee In Bok
 * Date      : 2024.09.10(Tue)
 * Runtime   : 42.28ms
 * Memory    : 105MB
 * Algorithm : Priority Queue
 */

package com.year2024.Week17;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LV2_155651 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(new String[][] {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}})
        );
    }

    static class Solution {
        public int solution(String[][] book_time) {
            Queue<Customer> waitingQ = new PriorityQueue<>(Comparator.comparing(a -> a.srtTime));
            Queue<Customer> roomQ = new PriorityQueue<>(Comparator.comparing(a -> a.endTime));
            int maxSize = 0;

            for(String[] times : book_time) {
                waitingQ.add(new Customer(getMinAndSec(times[0]), getMinAndSec(times[1])));
            }

            while(!waitingQ.isEmpty()) {
                Customer cus = waitingQ.poll();

                // 사용중인 호텔방 존재 && (체크인과 체크아웃 동시간 || 현 고객 체크인 시간보다 늦은 체크 아웃방)
                while(!roomQ.isEmpty() && (roomQ.peek().endTime.equals(cus.srtTime) || cus.srtTime.isAfter(roomQ.peek().endTime))) {
                    roomQ.poll();
                }

                roomQ.add(cus);
                maxSize = Math.max(maxSize, roomQ.size());
            }

            return maxSize;
        }

        private int[] getMinAndSec(String times) {
            return Arrays.stream(times.split(":")).mapToInt(Integer::parseInt).toArray();
        }

        class Customer {
            private final int CLEAN_TIME = 10;
            LocalDateTime srtTime;
            LocalDateTime endTime;

            public Customer(int[] srtTime, int[] endTime) {
                LocalDate nowDate = LocalDate.now();

                this.srtTime = LocalDateTime.of(nowDate, LocalTime.of(srtTime[0], srtTime[1]));
                this.endTime = LocalDateTime.of(nowDate, LocalTime.of(endTime[0], endTime[1])).plusMinutes(CLEAN_TIME);
            }
        }
    }
}
