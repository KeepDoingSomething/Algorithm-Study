package programmers.LV2_250136;

import java.util.*;

public class Solution {

    int[] pipeMax;
    int[][] land;
    final int OIL = 1;
    final int STONE = 0;

    public int solution(int[][] land) {
        this.land = land;
        this.pipeMax = new int[land[0].length];

        for(int row = 0; row < land.length; row++){
            for(int col = 0; col < land[0].length; col++){
                bfs(row, col);
            }
        }

        return Arrays.stream(pipeMax).max().getAsInt();
    }

    public void bfs(int row, int col){
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        if(land[row][col] == STONE) return;

        Queue<int[]> que = new LinkedList<>();

        que.add(new int[]{row, col});
        int oil = 1;
        Set<Integer> colSet = new HashSet<>();
        land[row][col] = STONE;
        colSet.add(col);

        while(!que.isEmpty()){
            int[] cur = que.poll();

            int curRow = cur[0];
            int curCol = cur[1];

            for(int i = 0; i < 4; i++){
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];
                boolean isValid
                        = nextRow >= 0
                        && nextCol >=0
                        && nextRow < land.length
                        && nextCol < land[0].length
                        && land[nextRow][nextCol] == OIL;
                if(isValid){
                    land[nextRow][nextCol] = STONE;
                    colSet.add(nextCol);
                    oil += 1;
                    que.add(new int[]{nextRow, nextCol});
                }
            }
        }

        for(int oilCol : colSet){
            pipeMax[oilCol] += oil;
        }

    }

}