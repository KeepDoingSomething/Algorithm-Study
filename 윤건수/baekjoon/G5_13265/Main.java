package baekjoon.G5_13265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(testCase-- > 0) {
            sb.append(solution()).append(System.lineSeparator());
        }

        System.out.println(sb);
    }


    static int COLOR_NONE = 0;
    static int COLOR_ONE = 1;
    static int COLOR_TWO = 2;
    static boolean[] visited;
    public static String solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int circleCnt = Integer.parseInt(st.nextToken());
        int lineCnt = Integer.parseInt(st.nextToken());
        Circle[] circles = new Circle[circleCnt + 1];

        for(int i = 1; i <= circleCnt; i++) {
            circles[i] = new Circle(i);
        }

        for(int i = 0; i < lineCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            circles[from].addLine(circles[to]);
            circles[to].addLine(circles[from]);
        }

        visited = new boolean[circleCnt + 1];
        boolean isPossible = true;
        for(int i = 1; i <= circleCnt; i++){
            if(visited[i]) continue;
            Circle start = circles[i];

            isPossible = isPossible && dfs(start, COLOR_ONE);
        }

        return isPossible ? "possible" : "impossible";
    }

    private static boolean dfs(Circle start, int checkColor){
        if(start.color == checkColor){
            return true;
        }else if(start.color != COLOR_NONE){
            return false;
        }

        boolean isCorrect = true;
        start.color = checkColor;
        visited[start.num] = true;
        int nextCheckColor = checkColor == COLOR_ONE ? COLOR_TWO : COLOR_ONE;
        for (Circle next : start.to) {
            isCorrect = isCorrect && dfs(next, nextCheckColor);
        }

        return isCorrect;
    }

    private static class Circle{
        int num;
        int color;
        List<Circle> to;

        Circle(int num) {
            this.num = num;
            color = COLOR_NONE;
            to = new ArrayList<>();
        }

        void addLine(Circle other){
            this.to.add(other);
        }
    }

}
