package programmers.LV2_169199;

import java.util.*;

public class Solution {

    String[] board;
    boolean[][] visited;
    int height;
    int width;

    private static class Point{
        int row;
        int col;
        int moveNum;

        Point(int row, int col, int moveNum){
            this.row = row;
            this.col = col;
            this.moveNum = moveNum;
        }
    }

    public int solution(String[] board) {
        this.board = board;
        height = board.length;
        width = board[0].length();
        visited = new boolean[height][width];

        Queue<Point> queue = new LinkedList<>();

        loop: for(int i = 0; i < height; i++){
            for(int j = 0 ; j < width; j++){
                char point = board[i].charAt(j);
                if(point == 'R'){
                    queue.add(new Point(i, j, 0));
                    visited[i][j] = true;
                    break loop;
                }
            }
        }

        int answer = -1;

        while(!queue.isEmpty()){

            Point curPoint = queue.poll();

            int curRow = curPoint.row;
            int curCol = curPoint.col;

            if(board[curRow].charAt(curCol) == 'G'){
                answer = curPoint.moveNum;
                break;
            }

            for(int i = 0; i < 4; i ++){
                Point movePoint = move(curPoint, i);
                int moveRow = movePoint.row;
                int moveCol = movePoint.col;
                if(!visited[moveRow][moveCol]){
                    visited[moveRow][moveCol] = true;
                    queue.add(movePoint);
                }
            }
        }

        return answer;
    }

    public Point move(Point start, int direction){

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};

        int curRow = start.row;
        int curCol = start.col;
        int nextRow = curRow + dr[direction];
        int nextCol = curCol + dc[direction];

        while(nextCol >= 0 && nextCol < width
                && nextRow >= 0 && nextRow < height
                && board[nextRow].charAt(nextCol) != 'D'){

            curCol = nextCol;
            curRow = nextRow;
            nextRow = curRow + dr[direction];
            nextCol = curCol + dc[direction];
        }

        return new Point(curRow, curCol, start.moveNum + 1);
    }

}