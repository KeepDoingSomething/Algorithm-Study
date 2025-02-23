/**
 * Author    : Kang San Ah
 * Date      : 2025.02.23(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 구현
 */

public class 서버증설횟수 {
    public static int solution(int[] players, int m, int k) {

        int[] running = new int[24 + k];
        int answer = 0;
        int active = 0; // 현재 가동 중인 서버 수

        for (int i = 0; i < 24; i++) {

            active += running[i];

            // 필요한 서버 수 계산
            int need = players[i] / m;

            // 부족한 서버만큼 추가
            if (need > active) {
                int newServers = need - active;
                answer += newServers;
                active += newServers;

                if (i + k < running.length) {
                    running[i + k] -= newServers;
                }
            }
        }

        return answer;
    }
}
