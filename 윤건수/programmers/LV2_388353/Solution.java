package programmers.LV2_388353;

import java.util.*;

public class Solution {

    final String EMPTY = "EMPTY";
    int rowLength;
    int colLength;
    int[] dr = {0, -1, 0, 1};
    int[] dc = {-1, 0, 1, 0};

    public int solution(String[] storage, String[] requests) {

        rowLength = storage.length;
        colLength = storage[0].length();
        String[][] curStorage = new String[rowLength][colLength];

        for(int i = 0; i < storage.length; i++){
            String[] containerLine = storage[i].split("");
            for(int j = 0; j < containerLine.length; j++){
                curStorage[i][j] = containerLine[j];
            }
        }


        for(String request : requests){
            if(request.length() == 2){
                crain(curStorage, String.valueOf(request.charAt(0)));
            }else{
                pickOutside(curStorage, request);
            }

            // for(String[] line: curStorage){
            //     System.out.println(Arrays.toString(line));
            // }
            // System.out.println("=================================");
        }

        int answer = 0;
        for(String[] storageLine : curStorage){
            for(String checkStorage: storageLine){
                if(!checkStorage.equals(EMPTY)) answer++;
            }
        }

        return answer;
    }

    public void crain(String[][] curStorage, String pickContainer){
        for(int i = 0; i < rowLength; i++){
            for(int j = 0; j < colLength; j++){
                if(curStorage[i][j].equals(pickContainer)){
                    curStorage[i][j] = EMPTY;
                }
            }
        }
    }

    public void pickOutside(String[][] curStorage, String pickContainer){
        Queue<Container> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowLength][colLength];
        for(int i = 0; i < rowLength; i++){
            for(int j = 0; j < colLength; j++){
                if(i == 0 || i == rowLength - 1 || j == 0 || j == colLength - 1){
                    que.add(new Container(i, j, curStorage[i][j]));
                }
            }
        }

        while(!que.isEmpty()){
            Container cur = que.poll();
            if(visited[cur.row][cur.col]) continue;
            visited[cur.row][cur.col] = true;

            if(cur.name.equals(pickContainer)){
                curStorage[cur.row][cur.col] = EMPTY;
                continue;
            }else if(!cur.name.equals(EMPTY)){
                continue;
            }

            for(int i = 0; i < dr.length; i++){
                int nextRow = cur.row + dr[i];
                int nextCol = cur.col + dc[i];

                if(nextRow >= 0
                        && nextCol >= 0
                        && nextRow < rowLength
                        && nextCol < colLength
                        && !visited[nextRow][nextCol]){
                    que.add(new Container(nextRow, nextCol, curStorage[nextRow][nextCol]));
                }
            }
        }
    }

    private static class Container {
        int row;
        int col;
        String name;

        Container(int row, int col, String name){
            this.row = row;
            this.col = col;
            this.name = name;
        }
    }

}