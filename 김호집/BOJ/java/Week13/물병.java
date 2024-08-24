/**
 * Author    : HojipKim
 * Date      : 2024.08.14(Wed)
 * Runtime   : 64 ms
 * Memory    : 11640 KB
 * Algorithm : 비트마스킹, 그리디 알고리즘, 수학
 */
import java.io.*;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0, index = -1;
        String bitN = Integer.toBinaryString(N);
        int ones = Integer.bitCount(N);
        if(ones > K){
            for (int i = 0; i < bitN.length(); i++) {
                if (K == 0) {
                    index = i;
                    break;
                }
                if (bitN.charAt(i) == '1')
                    K--;
            }
            String temp = bitN.substring(index);
            int t = Integer.parseInt(temp, 2);

            if (t != 0)
                answer = (int) (Math.pow(2, bitN.length() - index) - t);
        }
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}