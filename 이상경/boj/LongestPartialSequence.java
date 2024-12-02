import java.io.*;
import java.util.*;

public class LongestPartialSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num % 2 == 0;
        }

        int maxLen = 0;
        int left = 0;
        int right = 0;
        int count = 0;

        while (right < n) {
            if (count < k) {
                if (!arr[right]) {
                    count++;
                }
                right++;
                maxLen = Math.max(right - left - count, maxLen);
            } else if (arr[right]) {
                right++;
                maxLen = Math.max(right - left - count, maxLen);
            } else {
                if (!arr[left]) {
                    count--;
                }
                left++;
            }
        }

        System.out.println(maxLen);
    }
}