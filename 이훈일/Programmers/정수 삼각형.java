import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < triangle[i].length; j++) {
                if(j == 0) {
                    triangle[i][j] += triangle[i-1][j];
                } else if(j == triangle[i].length - 1) {
                    triangle[i][j] += triangle[i-1][j-1];
                } else {
                    int max = Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                    triangle[i][j] += max;
                }
            }
        }
        
        Arrays.sort(triangle[N-1]);
        
        return triangle[N-1][N-1];
    }
}
