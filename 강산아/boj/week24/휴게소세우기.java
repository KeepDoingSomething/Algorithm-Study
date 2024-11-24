/**
 * Author    : Kang San Ah
 * Date      : 2024.11.14(Thu)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 이분 탐색
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 휴게소세우기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[]arr = new int[N+2];

        arr[0] = 0;
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N+1] = L;
        Arrays.sort(arr);

        int left = 1;
        int right = L-1;

        while(left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;

            for(int i=1; i<arr.length; i++) {
                sum+=(arr[i] - arr[i-1] - 1) / mid;
            }

            if(sum > M) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        System.out.println(left);
    }
}
