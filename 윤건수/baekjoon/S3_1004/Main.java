package baekjoon.S3_1004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 어린왕자 (Silver 3)
// https://www.acmicpc.net/problem/1004

public class Main {

    static int result;
    static int[] start;
    static int[] end;
    static int[][] space;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {

            start = new int[2];
            end = new int[2];
            StringTokenizer st;

            st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            int spaceCount = Integer.parseInt(br.readLine());
            space = new int[spaceCount][3];
            for (int i = 0; i < spaceCount; i++) {
                st = new StringTokenizer(br.readLine());
                space[i][0] = Integer.parseInt(st.nextToken());
                space[i][1] = Integer.parseInt(st.nextToken());
                space[i][2] = Integer.parseInt(st.nextToken());
            }

            checkCase(spaceCount);
        }
    }

    private static void checkCase(int spaceCount){
        result = 0;
        for(int i = 0; i < spaceCount; i++){
            int[] curSpace = space[i];
            boolean isInStart = isInSpace(start, curSpace);
            boolean isInEnd = isInSpace(end, curSpace);
            if(isInStart ^ isInEnd) result++;
        }
        System.out.println(result);
    }

    private static boolean isInSpace(int[] point, int[] space){
        // x^2 + y^2 < r^2 이면 내부
        // x -> (체크하는 지점 X - 궤도의 중심 x)
        // y -> (체크하는 지점 y - 궤도의 중심 y)
        // r -> 궤도 반지름
        int pointX = point[0];
        int pointY = point[1];

        int spaceCenterX = space[0];
        int spaceCenterY = space[1];
        int radius = space[2];
        return Math.pow(pointX - spaceCenterX, 2) + Math.pow(pointY - spaceCenterY, 2) < Math.pow(radius, 2);
    }

}
