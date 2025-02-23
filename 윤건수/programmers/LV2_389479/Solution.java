package programmers.LV2_389479;

import java.util.*;

public class Solution {

    public int solution(int[] players, int m, int k) {
        List<Server> servers = new ArrayList<>();
        Server defaultServer = new Server(24);
        servers.add(defaultServer);

        List<Integer> endServers = new ArrayList<>();

        int answer = 0;
        for(int i = 0; i < players.length; i++){
            int player = players[i];
            for(int j = 0; j < servers.size(); j++){
                player -= m;
            }

            if(player >= 0){
                while(player >= 0){
                    Server newServer = new Server(k);
                    servers.add(newServer);
                    answer++;
                    player -= m;
                }
            }

            endServers.clear();
            for(int j = servers.size() - 1; j >= 0; j--){
                Server server = servers.get(j);
                server.remain--;
                if(server.remain == 0) endServers.add(j);
            }

            for(int endServer : endServers){
                servers.remove(endServer);
            }
        }

        return answer;
    }

    private class Server{
        int remain;

        Server(int remain){
            this.remain = remain;
        }
    }

}