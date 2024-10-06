package baekjoon.G5_1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int targetRow = Integer.parseInt(st.nextToken());
        int targetCol = Integer.parseInt(st.nextToken());

        int pointRow = (int) Math.pow(2, N) - 1;
        int pointCol = (int) Math.pow(2, N) - 1;
        long visit = (long) Math.pow(2, N) * (long) Math.pow(2, N);

        while(N-- > 0) {
            int blockLength = (int) Math.pow(2, N);
            int blockSize = (int) Math.pow(blockLength, 2);
            int checkRow = pointRow - blockLength;
            int checkCol = pointCol - blockLength;

            if(targetRow <= checkRow && targetCol <= checkCol) {
                pointRow = checkRow;
                pointCol = checkCol;
                visit = visit - (blockSize * 3);
            }else if(targetRow <= checkRow && targetCol > checkCol) {
                pointRow = checkRow;
                visit = visit - (blockSize * 2);
            }else if(targetRow > checkRow && targetCol <= checkCol) {
                pointCol = checkCol;
                visit = visit - (blockSize * 1);
            }
        }

        System.out.println(visit - 1);
    }
}
