import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0; // 증가 카운트
        List<Integer> list = new LinkedList<>(); // 서버 시간을 카운트 할 리스트 겸 서버 갯수 카운트

        for (int i = 0; i < players.length; i++) {
            int serverCount = players[i] / m; // 게임 이용자가 필요로 한 서버

            // 필요한 서버가 현재 리스트 크기보다 크면 추가
            if (serverCount > list.size()) {
                int count = serverCount - list.size(); // 부족한 서버 개수

                for (int j = 0; j < count; j++) {
                    list.add(k);
                    answer++;
                }
            }

            // 모든 서버의 남은 시간 감소
            list.replaceAll(value -> value - 1);

            // 시간이 만료된 서버 삭제 (뒤에서부터 순회)
            for (int j = list.size() - 1; j >= 0; j--) {
                if (list.get(j) == 0) {
                    list.remove(j);
                }
            }
        }
        return answer;
    }
}
