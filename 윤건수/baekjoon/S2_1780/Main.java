package baekjoon.S2_1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] cntAnswer;

    public static void main(String[] args) throws IOException {

        cntAnswer = new int[]{0, 0, 0};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = line[j];
            }
        }

        recursiveCheck(map, 0, map.length - 1, 0, map.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int cnt : cntAnswer) {
            sb.append(cnt).append(System.lineSeparator());
        }
        System.out.println(sb.toString().trim());
    }

    private static void recursiveCheck(int[][] map, int fromR, int toR, int fromC, int toC) {
        int checkNum = map[fromR][fromC];

        boolean isSameAllNum = true;
        loop: for(int i = fromR; i <= toR; i++) {
            for(int j = fromC; j <= toC; j++) {
                if(map[i][j] != checkNum){
                    isSameAllNum = false;
                    break loop;
                }
            }
        }

        if(isSameAllNum){
            cntAnswer[checkNum + 1]++;
        }else{
            int nextLength = (toR - fromR + 1) / 3;
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    int nextFromR = fromR + (i * nextLength);
                    int nextToR = nextFromR + nextLength - 1;
                    int nextFromC = fromC + (j * nextLength);
                    int nextToC = nextFromC + nextLength - 1;
                    recursiveCheck(map, nextFromR, nextToR, nextFromC, nextToC);
                }
            }
        }
    }

}
