package programmers.level2.과제진행하기;

import java.util.*;

public class Solution {

    public String[] solution(String[][] plans) {
        // 과제 시작 시간을 기준으로 정렬
        Arrays.sort(plans, Comparator.comparing(a -> a[1]));

        Stack<String[]> stack = new Stack<>();
        List<String> completed = new ArrayList<>();

        int currentTime = timeToMinutes(plans[0][1]); // 첫 번째 과제 시작 시간
        for (int i = 0; i < plans.length; i++) {
            String[] currentPlan = plans[i];
            String name = currentPlan[0];
            int startTime = timeToMinutes(currentPlan[1]);
            int playTime = Integer.parseInt(currentPlan[2]);

            // 현재 과제 전에 처리해야 할 남아있는 과제를 처리
            while (!stack.isEmpty() && currentTime < startTime) {
                String[] previousPlan = stack.pop();
                int remainingTime = Integer.parseInt(previousPlan[2]);

                if (currentTime + remainingTime <= startTime) {
                    // 이전 과제를 모두 완료할 수 있는 경우
                    currentTime += remainingTime;
                    completed.add(previousPlan[0]);
                } else {
                    // 이전 과제를 완료하지 못하는 경우, 남은 시간을 갱신해서 다시 스택에 넣음
                    previousPlan[2] = Integer.toString(remainingTime - (startTime - currentTime));
                    stack.push(previousPlan);
                    break;
                }
            }

            // 현재 과제를 스택에 추가
            stack.push(new String[]{name, currentPlan[1], Integer.toString(playTime)});

            // 현재 시간을 업데이트
            currentTime = startTime;
        }

        // 남아 있는 과제를 모두 완료
        while (!stack.isEmpty()) {
            completed.add(stack.pop()[0]);
        }

        // 완료된 과제 리스트를 배열로 변환하여 반환
        return completed.toArray(new String[0]);

    }

    // "HH:MM" 형식의 시간을 분으로 변환하는 함수
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
