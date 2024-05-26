/**
 * Author    : Heo jun boem
 * Date      : 2024.05.21(Wen)
 * Runtime   : 14228 KB
 * Memory    : 128 ms
 * Algorithm : Implementation
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        // 스위치 번호는 1부터 시작이므로 +1
        arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 처음 스위치 입력
        for (int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        n = Integer.parseInt(br.readLine());

        // 학생 성별 받은 수 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 남자일때
            if (a == 1) {
                boy(arr, b);
            }

            // 여자일때
            if (a == 2) {
                girl(arr, b);
            }
        }

        // 최종 스위치 출력
        for (int i = 1; i < arr.length; i++) {
            System.out.print(arr[i]);
            
            // 20개가 넘어갈 시 줄바꿈
            if (i % 20 == 0){
                System.out.println();
            }
            else
                System.out.print(" ");
        }
    }

    // 남자일때
    static void boy(int[] arr, int b) {
        // 받은수의 배수로 스위치 상태 변경
        for (int j = b; j < arr.length;) {
            if (arr[j] == 0)
                arr[j] = 1;
            else
                arr[j] = 0;

            j += b;
        }
    }

    // 여자일때
    static void girl(int[] arr, int b) {
        int left = b, right = b;

        // 좌우 대칭이면서 가장많은 수를 포함하는 구간 스위치 상태 변경
        while (left > 0 && right < arr.length) {

            // 양쪽 둘다 0일때
            if (arr[left] == 0 && arr[right] == 0){
                arr[left] = 1;
                arr[right] = 1;
            }
            // 둘다 1일때
            else if (arr[left] == 1 && arr[right] == 1) {
                arr[left] = 0;
                arr[right] = 0;
            }
            // 서로 상태가 다르므로 return
            else
                return;

            // 확인할 스위치 index 변경
            left--;
            right++;
        }
    }
}
