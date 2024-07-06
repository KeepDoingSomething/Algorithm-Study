package programmers.LV2_1844;

import java.util.*;

public class Solution {

    int answer;
    static final int WALL = 0;

    public int solution(int[][] maps) {

        answer = Integer.MAX_VALUE;
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0, 0, 1));

        maps[0][0] = WALL;
        int goalX = maps.length - 1;
        int goalY = maps[0].length - 1;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while(!que.isEmpty()){
            Point now = que.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowTurn = now.turn;

            if(nowX == goalX && nowY == goalY){
                answer = nowTurn;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                boolean isValidIdx = nextX >= 0 && nextY >= 0 && nextX <= goalX && nextY <= goalY;
                if (isValidIdx && maps[nextX][nextY] != WALL) {
                    maps[nextX][nextY] = WALL;
                    que.add(new Point(nextX, nextY, nowTurn + 1));
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static class Point{
        int x;
        int y;
        int turn;

        Point(int x, int y, int turn){
            this.x = x;
            this.y = y;
            this.turn = turn;
        }
    }

}