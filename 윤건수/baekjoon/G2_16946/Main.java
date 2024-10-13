package baekjoon.G2_16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int WALL = 1;
    static final int EMPTY = 0;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int[][] map = new int[row][col];
        visited = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            String line = br.readLine();
            for(int j = 0; j < col; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(map[i][j] == EMPTY && !visited[i][j]){
                    checkCount(map, i, j);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int[] answerRow : map) {
            for (int answerCol : answerRow) {
                result.append(answerCol % 10);
            }
            result.append(System.lineSeparator());
        }
        result.deleteCharAt(result.length() - 1);
        System.out.println(result);
    }

    private static void checkCount(int[][] map, int row, int col) {
        visited[row][col] = true;
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(row, col));

        Set<Point> wallList = new HashSet<>();
        int count = 1;
        while(!que.isEmpty()){
            Point curPoint = que.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curPoint.row + dr[i];
                int nextCol = curPoint.col + dc[i];
                boolean isValidNext = isValid(visited, map, nextRow, nextCol);
                if (isValidNext && map[nextRow][nextCol] == EMPTY
                ) {
                    count++;
                    visited[nextRow][nextCol] = true;
                    que.add(new Point(nextRow, nextCol));
                } else if (isValidNext && map[nextRow][nextCol] != EMPTY
                ) {
                    wallList.add(new Point(nextRow, nextCol));
                }
            }
        }

        for(Point wall : wallList) {
            map[wall.row][wall.col] += count;
        }

    }

    private static boolean isValid(boolean[][] visited, int[][] map, int row, int col){
        return row >= 0 && col >= 0
                && row < map.length && col < map[0].length
                && !visited[row][col];
    }

    private static class Point{
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Point)) return false;
            Point other = (Point) o;
            return this.row == other.row && this.col == other.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

}
