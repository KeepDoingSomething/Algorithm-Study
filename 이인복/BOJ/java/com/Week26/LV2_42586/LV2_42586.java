/**
 * Author    : Lee In Bok
 * Date      : 2024.11.29(Fri)
 * Runtime   : 713.14 ms
 * Memory    : 258 MB
 * Algorithm : Queue
 */

package com.Week26.LV2_42586;

import java.util.*;

public class LV2_42586 {
    public static void main(String[] args) {

    }

    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            // 반환 타입은 배열 이지만 정답의 개수를 예측할 수 없어 동적인 리스트 생성 (단순 추가니 ArrayList 로)
            List<Integer> ans = new ArrayList<>();
            Queue<Process> q = new LinkedList<>();
            int day = 1;

            for(int i = 0; i < progresses.length; i++) {
                q.add(new Process(progresses[i], speeds[i]));
            }

            while(!q.isEmpty()) {
                int cnt = 0;

                // 100% 달성한 기능이 있다면 카운트 및 큐에서 제거
                while(!q.isEmpty() && q.peek().isFinished(day)) {
                    q.poll();
                    cnt++;
                }

                day++;  // 하루 증가

                // 완료된 기능이 존재하면 리스트로 반환
                if(cnt > 0) {
                    ans.add(cnt);
                }
            }

            return ans.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    class Process {
        int percent;
        int speed;

        Process(int percent, int speed) {
            this.percent = percent;
            this.speed = speed;
        }

        public boolean isFinished(int day) {
            return percent + (speed * day) >= 100;
        }
    }
}