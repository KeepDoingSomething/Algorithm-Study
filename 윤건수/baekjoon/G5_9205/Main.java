package baekjoon.G5_9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final String SUCCESS = "happy";
    static final String FAIL = "sad";
    static BufferedReader br;
    static Point home;
    static Point festival;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        while (testCase-- > 0) {
            answer.append(solution()).append(System.lineSeparator());
        }

        System.out.println(answer);
    }

    public static String solution() throws IOException {
        int pointNum = Integer.parseInt(br.readLine()) + 2;
        Point[] points = new Point[pointNum];

        for(int i = 0; i < pointNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point point = new Point(x, y);
            if(i == 0 ){
                home = point;
            }else if (i == pointNum - 1){
                festival = point;
            }
            points[i] = point;
        }

        for(int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                if(i == j) continue;
                Point from = points[i];
                Point to = points[j];

                if(from.canGo(to)){
                    from.graphs.add(to);
                    to.graphs.add(from);
                };
            }
        }

        // home.isVisited = true;
        // boolean isArriveFestival = checkArriveDfs(home);
        Stack<Point> stack = new Stack<>();
        stack.push(home);

        boolean isArriveFestival = false;
        while(!stack.isEmpty()){
            Point now = stack.pop();
            if(now.equals(festival)){
                isArriveFestival = true;
                break;
            }

            for(Point next : now.graphs){
                if(next.isVisited) continue;
                next.isVisited = true;
                stack.push(next);
            }
        }


        return isArriveFestival ? SUCCESS : FAIL;
    }

    public static boolean checkArriveDfs(Point now){
        if(now.equals(festival)){
            return true;
        }

        for(Point next : now.graphs){
            if(next.isVisited) continue;

            next.isVisited = true;
            if(checkArriveDfs(next)){
                return true;
            };

            next.isVisited = false;
        }

        return false;
    }

    public static class Point{
        int x;
        int y;
        boolean isVisited;
        Set<Point> graphs;

        Point(int x, int y){
            this.x = x;
            this.y = y;
            graphs = new HashSet<>();
            isVisited = false;
        }

        public int getDistance(Point other){
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }

        public boolean canGo(Point other){
            return getDistance(other) <= (20 * 50);
        }
    }

}
