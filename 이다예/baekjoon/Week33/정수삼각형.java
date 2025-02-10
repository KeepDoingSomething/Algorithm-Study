import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        for(int i = triangle.length-1; i>0; i--){
            for(int j =0 ; j<i; j++){
                triangle[i-1][j] = Math.max(triangle[i-1][j]+ triangle[i][j], triangle[i-1][j] + triangle[i][j+1]);
            }
        }
        
        return triangle[0][0];
    }
}