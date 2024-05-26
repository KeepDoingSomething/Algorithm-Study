package baekjoon.silver.silver4.boj1244_스위치켜고끄기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int SWITCH_ON = 1;
    static final int SWITCH_OFF = 0;
    static int N; // 스위치의 갯수
    static int[] status; // 스위치의 상태
    static int numOfStudents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        status = new int[N];
        for (int i = 0; i < N; i++) {
            status[i] = Integer.parseInt(st.nextToken());
        }

        numOfStudents = Integer.parseInt(br.readLine());
        for (int i = 0; i < numOfStudents; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (gender == 1) { // 남자
                // num의 배수 상태 바꾸기
                toggleMaleSwitches(num);
            } else if (gender == 2) { // 여자
                // num 중심  좌우 대칭 가장 긴 구간 상태 바꾸기
                toggleFemaleSwitches(num - 1);
            }
        }

        printStatus();
    }

    private static void printStatus() {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < N) {
            sb.append(status[idx++]);
            sb.append(idx % 20 == 0 ? System.lineSeparator() : ' ');
        }
        System.out.println(sb);
    }

    private static void toggleFemaleSwitches(int num) {
        int left = num - 1;
        int right = num + 1;
        toggleSwitch(num);

        while (left >= 0 && right < N && status[left] == status[right]) {
            toggleSwitch(left--);
            toggleSwitch(right++);
        }
    }

    private static void toggleMaleSwitches(int num) {
        for (int i = num - 1; i < N; i+= num) {
            toggleSwitch(i);
        }
    }

    private static void toggleSwitch(int i) {
        status[i] = status[i] == SWITCH_ON ? SWITCH_OFF : SWITCH_ON;
    }
}
