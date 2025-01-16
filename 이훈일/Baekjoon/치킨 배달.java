package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon15686 {
    static int N, M;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    static int[][] result;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                String point = st.nextToken();
                if(point.equals("2")) {
                    chickens.add(new int[]{i+1, j+1});
                } else if(point.equals("1")) {
                    houses.add(new int[]{i+1, j+1});
                }
            }
        }

        result = new int[M][2];
        answer = Integer.MAX_VALUE;
        backtracking(0, 0);

        System.out.println(answer);
    }

    public static void backtracking(int depth, int start) {
        if(depth == M) {
            int cityLen = calCityLen();
            answer = Math.min(answer, cityLen);
            return;
        }

        for(int i = start; i < chickens.size(); i++) {
            result[depth][0] = chickens.get(i)[0];
            result[depth][1] = chickens.get(i)[1];
            backtracking(depth + 1, i + 1);
        }
    }

    public static int calCityLen() {
        int chickenLen = 0;
        for(int i = 0; i < houses.size(); i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < M; j++) {
                int xLen = Math.abs(houses.get(i)[0] - result[j][0]);
                int yLen = Math.abs(houses.get(i)[1] - result[j][1]);
                int len = xLen + yLen;
                min = Math.min(min, len);
            }
            chickenLen += min;
        }
        return chickenLen;
    }
}
