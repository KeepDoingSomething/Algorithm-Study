
import java.util.*;

public class Solution {
    List<String> trails = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", tickets, 0);

        trails.sort(Comparator.naturalOrder());

        return trails.get(0).split(" ");
    }

    public void dfs(String start, String route, String[][] tickets, int cnt) {
        if (cnt == tickets.length) {
            trails.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, cnt + 1);
                visited[i] = false;
            }
        }

    }

}
