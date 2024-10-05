/**
 * Author    : Kang San Ah
 * Date      : 2024.10.06(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 구현
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z {
    static int size = 1;
    static int N, r, c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);

        int count = 0;
        int x = 0;
        int y = 0;

        while(size > 0) {
            size /= 2;
            if(r < x+size && c < y+size) {
                count += 0;
            }
            else if(r < x+size) {
                count += size * size;
                y += size;
            }
            else if (c < y+size) {
                count += size * size * 2;
                x += size;
            }
            else {
                count += size * size * 3;
                x += size;
                y += size;
            }


            // size가 1이 되면 종료.
            if(size == 1) {
                System.out.println(count);
                break;
            }
        }

    }
}
