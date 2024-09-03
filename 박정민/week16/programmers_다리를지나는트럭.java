import java.util.*;
import java.io.*;

class Solution {
    static int[] now;
    static int nowW,answer;
    public int solution(int bridge_length, int weight, int[] truck_weights) {
    
        now = new int[bridge_length];
        
        Queue<Integer> q = new LinkedList<>();
        for(int t:truck_weights)
        {
            q.add(t);
        }
        now[0]=q.poll();
        answer++;
        nowW+=now[0];
        
        while(!q.isEmpty())
        {
            nowW-=now[bridge_length-1];
             for(int i=bridge_length-1;i>=1;i--)
            {
                            now[i]=now[i-1];
                            now[i-1]=0;
                                     
             }
            if(nowW+q.peek()<=weight)
            {
               int n = q.poll();
                now[0]=n;
                nowW+=n;
            }
            answer++;
        }
        for(int i=0;i<bridge_length;i++)
        {
            if(now[i]!=0)
            {
                int idx= i;
                answer+= bridge_length - idx;
                break;
            }
        }
        
        return answer;
    }
}
