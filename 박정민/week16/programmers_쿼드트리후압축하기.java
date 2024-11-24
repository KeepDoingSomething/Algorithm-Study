import java.util.*;
import java.io.*;

class Solution {
    static int N;
    static int zeroCnt,oneCnt;
    static boolean[][] v;
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        
        N= arr[0].length;
        v= new boolean[N][N];
        dfs(0,0,N,arr);
        answer[0]=zeroCnt;
        answer[1]=oneCnt;
        return answer;
    }
    public static void dfs(int x,int y,int n,int[][] board)
    {
            
        int number= board[x][y];
       
        boolean flag = true;
        
        int zero=0;
        int one=0;
        for(int i=x;i<x+n;i++)
        {
            for(int j=y;j<y+n;j++)
            {
                if(board[i][j]!=number)
                {
                    flag= false;
                    //break;
                }
             
            }
        
        }
         int newSize= n/2;
        if(flag)
        {
            if(number==0)
            {
                zeroCnt++;
            }
            else if(number==1){
                oneCnt++;
            }
            return;
        }
        
        dfs(x,y,newSize,board);
        dfs(x,y+newSize,newSize,board);
        dfs(x+newSize,y,newSize,board);
        dfs(x+newSize,y+newSize,newSize,board);
    
    
        
        
    }
}
