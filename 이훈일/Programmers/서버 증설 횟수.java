import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int n = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < players.length; i++) {
            int player = players[i];
            
            for(int index : map.keySet()) {
                if(i-index == k) {
                    n -= map.get(index);
                }    
            }
            
            if(player >= (n+1)*m) {
                if(player/m > n) {
                    int remain = player/m - n;
                    count += remain;
                    n += remain;
                    map.put(i, remain);
                }
            }
        }
        
        return count;
    }
}
