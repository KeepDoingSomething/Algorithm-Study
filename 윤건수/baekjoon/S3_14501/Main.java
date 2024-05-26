package baekjoon.S3_14501;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 퇴사 (Silver 3)
// https://www.acmicpc.net/problem/14501
public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int day = Integer.parseInt(br.readLine());
        int[] bestIncome = new int[day + 2]; // 오늘부터 퇴사일까지 벌 수 있는 최대 수입
        int[] period = new int[day + 1]; // 상담 시작시 경과일
        int[] incomeForStart = new int[day + 1]; // 해당 날짜에 상담 시작시 벌 수 있는 금액
        for (int i = 1; i <= day; i++) {
            st = new StringTokenizer(br.readLine());
            period[i] = Integer.parseInt(st.nextToken());
            incomeForStart[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = day; i > 0; i--) {
            if (i + period[i] > day + 1) { // 입력된 상담 정보가 상담 종료일까지 못끝나는 경우
                bestIncome[i] = bestIncome[i + 1]; // 오늘 시작하는 상담을 할 수 없으므로 다음날과 bestIncome[i]가 같음
            } else {
                bestIncome[i] = Math.max(bestIncome[i + 1], incomeForStart[i] + bestIncome[i + period[i]]); // 상담을 안할경우의 수입 vs 할 경우의 수입을 비교하여 상담 선택
            }
        }

        System.out.println(bestIncome[1]);
    }

}
