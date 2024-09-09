package programmers.LV3_60061;

import java.util.*;

public class Solution {

    private static final int COLUMN = 0;
    private static final int BEAM = 1;

    private static final int REMOVE = 0;
    private static final int CREATE = 1;

    boolean[][] beam;
    boolean[][] column;
    TreeSet<int[]> resultSet;
    int n;

    public int[][] solution(int n, int[][] build_frame) {

        this.n = n;
        this.beam = new boolean[n + 1][n + 1];
        this.column = new boolean[n + 1][n + 1];
        this.resultSet = new TreeSet<>((arr1, arr2) -> {
            if(arr1[0] == arr2[0]){
                if(arr1[1] == arr2[1]){
                    return arr1[2] - arr2[2];
                }else{
                    return arr1[1] - arr2[1];
                }
            }
            return arr1[0] - arr2[0];
        });

        for(int[] frame : build_frame){
            int x = frame[0];
            int y = frame[1];
            int columnOrBeam = frame[2];
            int removeOrCreate = frame[3];

            if(columnOrBeam == COLUMN){
                if(removeOrCreate == REMOVE && isRemovableColumn(x, y)){
                    removeColumn(x, y);
                }else if(removeOrCreate == CREATE && isCreatableColumn(x, y)){
                    createColumn(x, y);
                }
            }else{
                if(removeOrCreate == REMOVE && isRemovableBeam(x, y)){
                    removeBeam(x, y);
                }else if(removeOrCreate == CREATE && isCreatableBeam(x, y) ){
                    createBeam(x, y);
                }
            }
        }

        int[][] answer = new int[resultSet.size()][3];
        int idx = 0;
        for(int[] item : resultSet){
            answer[idx++] = item;
        }
        return answer;
    }

    public boolean isValidIdx(int x, int y){
        return x >= 0 && y >= 0 && x <= n && y <= n;
    }

    public boolean isCreatableBeam(int x, int y){
        boolean isCreatable = false;
        // 바닥 ??
        isCreatable = y == 0;

        // 왼쪽 끝 기둥있을 시 
        isCreatable = isCreatable || (isValidIdx(x, y - 1) && column[x][y - 1]);
        // 오른쪽 끝 기둥있을 시
        isCreatable = isCreatable || (isValidIdx(x + 1, y - 1) && column[x + 1][y - 1]);
        // 양쪽에 보 있을시
        isCreatable = isCreatable
                || (isValidIdx(x - 1, y) && beam[x - 1][y] && isValidIdx(x + 1, y) && beam[x + 1][y]);
        return isCreatable;
    }

    public void createBeam(int x, int y){
        beam[x][y] = true;
        resultSet.add(new int[]{x, y, BEAM});
    }

    public boolean isCreatableColumn(int x, int y){
        boolean isCreatable = false;
        // 바닥일 시
        isCreatable = y == 0;
        // 왼쪽에 보 있을 시 
        isCreatable = isCreatable || (isValidIdx(x - 1, y) && beam[x -1 ][y]);
        // 오른쪽으로 보 있을 시
        isCreatable = isCreatable || (isValidIdx(x, y) && beam[x][y]);
        // 아래에 기둥 있을시
        isCreatable = isCreatable || (isValidIdx(x, y - 1) && column[x][y - 1]);

        return isCreatable;
    }

    public void createColumn(int x, int y){
        column[x][y] = true;
        resultSet.add(new int[]{x, y, COLUMN});
    }

    public boolean isRemovableBeam(int x, int y){
        beam[x][y] = false;
        boolean isRemovable = true;
        // 왼쪽 보 괜찮은지
        isRemovable = isRemovable
                && (!isValidIdx(x - 1, y) || !beam[x - 1][y] || isCreatableBeam(x - 1, y));
        // 오른쪽 보 괜찮은지
        isRemovable = isRemovable
                && (!isValidIdx(x + 1, y) || !beam[x + 1][y] || isCreatableBeam(x + 1, y));
        // 왼쪽 기둥 괜찮은지
        isRemovable = isRemovable
                && (!column[x][y] || isCreatableColumn(x, y));
        // 오른쪽 기둥 괜찮은지
        isRemovable = isRemovable
                && (!isValidIdx(x + 1, y) || !column[x + 1][y] || isCreatableColumn(x + 1, y));

        beam[x][y] = true;

        return isRemovable;
    }

    public void removeBeam(int x, int y){
        beam[x][y] = false;
        resultSet.remove(new int[]{x, y, BEAM});
    }

    public boolean isRemovableColumn(int x, int y){

        column[x][y] = false;
        boolean isRemovable = true;
        // 위 기둥 괜찮은지
        isRemovable = isRemovable
                && (!isValidIdx(x, y + 1) || !column[x][y + 1] || isCreatableColumn(x, y + 1));
        // 좌상단 보 괜찮은지
        isRemovable = isRemovable
                && (!isValidIdx(x - 1, y + 1) || !beam[x - 1][y + 1] || isCreatableBeam(x - 1, y + 1));
        // 우상단 보 괜찮은지
        isRemovable = isRemovable
                && (!isValidIdx(x, y + 1) || !beam[x][y + 1] || isCreatableBeam(x, y + 1));


        column[x][y] = true;

        return isRemovable;
    }

    public void removeColumn(int x, int y){
        column[x][y] = false;
        resultSet.remove(new int[]{x, y, COLUMN});
    }

}