/**
 * Author    : Heo jun boem
 * Date      : 2024.05.14(Tue)
 * Runtime   : 225584 KB
 * Memory    : 1740 ms
 * Algorithm : Binary Search
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            StringBuilder sb = new StringBuilder();

            int an = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arrOne = new int[an];
            for (int i = 0; i < an; i++){
                arrOne[i] = Integer.parseInt(st.nextToken());
            }

            int bn = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            Arrays.sort(arrOne);

            for (int i = 0; i < bn; i++) {

                int start = 0, end = an-1;
                int val = Integer.parseInt(st.nextToken());
                boolean find = false;

                while (start <= end) {
                    int mid = (start + end) / 2;

                    if (val == arrOne[mid]) {
                        find = true;
                        break;
                    } else if (val > arrOne[mid]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }

                sb.append(find? 1 : 0).append("\n");
            }
            System.out.print(sb);
        }
    }
}
