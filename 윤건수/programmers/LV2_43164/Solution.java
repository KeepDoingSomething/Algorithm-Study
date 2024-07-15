package programmers.LV2_43164;

import java.util.*;

public class Solution {

    Map<String, List<Ticket>> ticketInfos;
    final static String START_PORT = "ICN";
    int totalTicketNum;
    String result;

    public String[] solution(String[][] tickets) {

        ticketInfos = new HashMap<>();
        totalTicketNum = tickets.length;
        result = "";

        for(String[] ticket : tickets){
            String from = ticket[0];
            String to = ticket[1];
            Ticket newTicket = new Ticket(to);

            if(!ticketInfos.containsKey(from)){
                List<Ticket> fromTickets = new ArrayList<>();
                ticketInfos.put(from, fromTickets);
            }

            List<Ticket> fromTickets = ticketInfos.get(from);
            fromTickets.add(newTicket);
        }

        String route = START_PORT;

        dfs(START_PORT, route, 0);

        return result.split(",");
    }

    private void dfs(String from, String route, int depth){
        if(depth == totalTicketNum){
            if(result.length() == 0){
                result = route;
            }else{
                result = route.compareTo(result) < 0 ? route : result;
            }
            return;
        }

        if(!ticketInfos.containsKey(from)) return;

        List<Ticket> fromTickets = ticketInfos.get(from);

        for(Ticket next : fromTickets){
            if(next.isUsed) continue;
            next.isUsed = true;
            route += "," + next.to;
            dfs(next.to, route, depth + 1);
            route = route.substring(0, route.length() - 4);
            next.isUsed = false;
        }
    }

    private static class Ticket{

        String to;
        boolean isUsed = false;;

        Ticket(String to){
            this.to = to;
        }

        @Override
        public String toString(){
            return this.to;
        }
    }

}