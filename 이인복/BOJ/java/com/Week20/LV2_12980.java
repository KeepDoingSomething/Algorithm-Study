/**
 * Author    : Lee In Bok
 * Date      : 2024.10.07(Mon)
 * Runtime   : 0.03 ms
 * Memory    : 78 MB
 * Algorithm : Implement
 */

package com.Week20;

public class LV2_12980 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.solution(5));
    }

    public static class Solution {
        public int solution(int n) {
            int ans = 0;

            while(n != 0) {
                if(n % 2 == 1) {
                    n--;
                    ans++;
                } else {
                    n /= 2;
                }
            }

            return ans;
        }
    }
}

/*
import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        Queue<Record> q = new LinkedList();
        Set<Integer> visited = new HashSet();

        q.add(new Record(0, 0));
        visited.add(0);

        while(!q.isEmpty()) {
            Record curRecord = q.poll();

            if(curRecord.loc > n) {
                continue;
            }

            if(curRecord.loc == n) {
                ans = curRecord.move;
            }

            int jumpToNext = curRecord.loc + 1;
            int teleportToNext = curRecord.loc * 2;

            if(!visited.contains(teleportToNext)) {
                q.add(new Record(teleportToNext, curRecord.move));
                visited.add(teleportToNext);
            }

            if(!visited.contains(jumpToNext)) {
                q.add(new Record(jumpToNext, curRecord.move + 1));
                visited.add(jumpToNext);
            }
        }

        return ans;
    }

    class Record {
        int loc;
        int move;

        Record(int loc, int move) {
            this.loc = loc;
            this.move = move;
        }
    }
}
*/