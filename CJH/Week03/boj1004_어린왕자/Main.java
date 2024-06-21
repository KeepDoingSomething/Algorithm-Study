package baekjoon.silver.silver3.boj1004_어린왕자;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/baekjoon/silver/silver3/boj1004_어린왕자/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(br.readLine());

            int answer = 0;
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                // 원의 중점까지의 거리가 같거나 작으면 원 안에 위치
                // 2개 다 원의 안에 위치하면 진입/이탈 없음
                // 1개의 점만 원의 안에 위치해야 진입 or 이탈 존재
                //  1. 원의 중점과 출발점 거리가 반지름 보다 작고 도착점과의 거리는 반지름보다 커야 함
                //  2. 원의 중점과 출발점 거리가 반지름 보다 크고 도착점과의 거리는 반지름보다 작아야 함
                boolean isStartPointInCircle = (Math.pow(x1 - cx, 2) + Math.pow(y1 - cy, 2)) <= Math.pow(r, 2);
                boolean isEndPointInCircle = (Math.pow(x2 - cx, 2) + Math.pow(y2 - cy, 2)) <= Math.pow(r, 2);

                if (isStartPointInCircle ^ isEndPointInCircle) { // XOR 연산 두 변수가 다른 경우만 참
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }
}
