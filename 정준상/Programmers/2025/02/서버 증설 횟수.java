import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-02-17
 * Runtime          :   0.90 ms
 * Memory           :   92.3 MB
 * Used algorithm   :   Queue, Implementation
 */
class Solution {

    public int solution(int[] players, int m, int k) {
        int answer = 0;

        Queue<Server> expandedServers = new LinkedList<>();

        for (int time = 0; time < players.length; time++) {

            int remainServers = disposeExpandedServers(
                    time, k, expandedServers
            );

            int requiredServers = requiredServer(m, players[time]);

            // 서버가 충분
            if (requiredServers <= remainServers) {
                continue;
            }

            // 부족하면 증설
            int addition = requiredServers - remainServers;
            answer += addition;
            expandedServers.add(new Server(time, addition));
        }

        return answer;
    }

    // 빌린 서버 반납 후, 남은 서버 개수를 return
    private int disposeExpandedServers(
            int currentTime, int k, Queue<Server> expandedServers
    ) {

        Server borrwoedServer;
        while ((borrwoedServer = expandedServers.peek()) != null &&
                borrwoedServer.borrowedTime + k <= currentTime
        ) {
            expandedServers.poll();
        }

        int numOfRemains = 0;
        for (Server server : expandedServers) {
            numOfRemains += server.borrowedNumber;
        }

        return numOfRemains;
    }

    // 유저 수, m 에 따라 필요한 서버 개수 확인
    private int requiredServer(int m, int currentPlayers) {
        return currentPlayers / m;
    }
}

// 편의로 만든 class
class Server {

    int borrowedTime;
    int borrowedNumber;

    public Server(int borrowedTime, int borrowedNumber) {
        this.borrowedTime = borrowedTime;
        this.borrowedNumber = borrowedNumber;
    }
}
