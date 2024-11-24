/**
 * Author    : HojipKim
 * Date      : 2024.10.19(Sat)
 * Runtime   : 72 ms
 * Memory    : 13052 KB
 * Algorithm : 분할정복
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int[][] arr;


    public static int R;
    public static int C;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Main.R = C;
        Main.C = R;

        divideConquer(0, 0, (int) Math.pow(2, N));


    }

    public static void divideConquer(int x, int y, int length){
        if(length == 0){
            if(x == R && y == C){
                System.out.println(answer);
                System.exit(0);
            }
            answer += 1;
            return;
        }

        int half = length / 2;
        // 1사분면
        if((x <= R && R < x+half) && (y <= C && C < y+half)){
            divideConquer(x, y, half);
        }else {
            answer += half*half;
        }
        // 2사분면
        if ((x + half <= R && R < x+length) && (y <= C && C < y+half)) {
            divideConquer(x+half, y, half);
        }else {
            answer += half*half;
        }
        // 3사분면
        if((x<= R && R < x+half) && (y+half <= C && C < y+length)){
            divideConquer(x, y+half, half);
        }else{
            answer += half*half;
        }
        // 4사분면
        if((x+half <= R && R < x+length) && (y+half <= C && C < y+length)){
            divideConquer(x+half, y+half, half);
        }else{
            answer += half*half;
        }

    }
}
