package programmers.level2.조이스틱;

public class Solution {
    public int solution(String name) {
        int length = name.length();
        int answer = 0;
        int move = name.length() - 1;

        for (int i = 0; i < length; i++) {
            // 상하 이동 횟수 계산
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            int index = i + 1;
            while (index < length && name.charAt(index) == 'A') {
                index++;
            }

            // 두 가지 경우를 고려하여 최소 이동 횟수 계산
            // 1. 현재 위치 i까지 갔다가 다시 돌아와서 남은 문자 방문
            move = Math.min(move, i * 2 + length - index);

            // 2. 남은 문자들을 방문하고 다시 현재 위치로 돌아옴
            move = Math.min(move, (length - index) * 2 + i);
        }

        answer += move;

        return answer;
    }
}