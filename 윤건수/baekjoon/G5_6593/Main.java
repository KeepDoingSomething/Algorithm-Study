package baekjoon.G5_6593;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 상범 빌딩 (Gold 5)
// https://www.acmicpc.net/problem/6593

public class Main {

    static char[][][] building;
    final static char START = 'S';
    final static char END = 'E';
    final static char FORBIDDEN = '#';
    static Point startPoint;
    static Point endPoint;
    static int floorNum;
    static int rowNum;
    static int colNum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st;
        String line;
        while(!(line = br.readLine()).equals("0 0 0")){
            st = new StringTokenizer(line);
            floorNum = Integer.parseInt(st.nextToken());
            rowNum = Integer.parseInt(st.nextToken());
            colNum = Integer.parseInt(st.nextToken());
            building = new char[floorNum][rowNum][colNum];

            // 빌딩 정보 초기화
            for(int f = 0; f < floorNum; f++){
                for (int r = 0; r < rowNum; r++) {
                    building[f][r] = br.readLine().toCharArray();
                    for(int c = 0; c < colNum; c++){

                        if(building[f][r][c] == START){
                            startPoint = new Point(f, r, c);
                        };

                        if(building[f][r][c] == END){
                            endPoint = new Point(f, r, c);
                        };
                    }
                }

                // 층 공백 처리
                br.readLine();
            }

            String message = bfs();
            result.append(message);
            result.append(System.lineSeparator());
        }

        System.out.println(result.toString());
    }

    private static String bfs() {

        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        int time = 0;

        while(!queue.isEmpty()){
            int[] df = {1, -1, 0, 0, 0, 0};
            int[] dr = {0, 0, 1, -1, 0, 0};
            int[] dc = {0, 0, 0, 0, 1, -1};
            Point curPoint = queue.poll();

            if (curPoint.isSamePlace(endPoint)) {
                time = curPoint.passTime;
                break;
            }

            for (int i = 0; i < 6; i++) {
                int nextFloor = curPoint.floor + df[i];
                int nextRow = curPoint.row + dr[i];
                int nextCol = curPoint.col + dc[i];
                boolean isValidFloor = nextFloor >= 0 && nextFloor < floorNum;
                boolean isValidRow = nextRow >= 0 && nextRow < rowNum;
                boolean isValidCol = nextCol >= 0 && nextCol < colNum;
                boolean isValidIdx = isValidFloor && isValidRow && isValidCol;
                if(isValidIdx && building[nextFloor][nextRow][nextCol] != FORBIDDEN){
                    building[nextFloor][nextRow][nextCol] = FORBIDDEN;
                    Point next = new Point(nextFloor, nextRow, nextCol);
                    next.setPassTime(curPoint.passTime + 1);
                    queue.add(next);
                }
            }
        }

        return time == 0 ? "Trapped!" : "Escaped in " + time + " minute(s).";
    }

    private static class Point{
        int floor;
        int row;
        int col;
        int passTime = 0;

        Point(int floor, int row, int col) {
            this.floor = floor;
            this.row = row;
            this.col = col;
        }

        public void setPassTime(int passTime) {
            this.passTime = passTime;
        }

        public boolean isSamePlace(Point p){
            return this.floor == p.floor && this.row == p.row && this.col == p.col;
        }

    }

}
