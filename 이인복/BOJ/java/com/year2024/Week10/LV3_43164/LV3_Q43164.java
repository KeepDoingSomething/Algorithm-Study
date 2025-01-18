package com.year2024.Week10.LV3_43164;

import java.util.*;

public class LV3_Q43164 {
    public static void main(String[] args) {
        Solution sol = new Solution();
    }

    static class Solution {

        public final String START_LOC = "ICN";

        public boolean[] visited;
        public List<String> path = new ArrayList<>();
        public String[] ans;

        public String[] solution(String[][] tickets) {
            Arrays.sort(tickets, (a, b) -> {
                if(a[0].equals(b[0])) {
                    return b[1].compareTo(a[1]);
                }
                return a[0].compareTo(b[0]);
            });

            visited = new boolean[tickets.length];

            path.add(START_LOC);
            dfs(tickets, START_LOC);  // 초기 index, 시작 공항명

            return ans;
        }

        public void dfs(String[][] tickets, String srtLoc) {
            if(tickets.length + 1 == path.size()) {
                ans = path.toArray(new String[0]);
                return;
            }

            for(int i = 0; i < tickets.length; i++) {
                if(!visited[i] && tickets[i][0].equals(srtLoc)) {
                    visited[i] = true;
                    path.add(tickets[i][1]);
                    dfs(tickets, tickets[i][1]);
                    path.remove(path.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}
