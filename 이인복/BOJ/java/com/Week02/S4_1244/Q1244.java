package com.Week02.S4_1244;

import com.self.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1244 implements Solution {

    @Override
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Boolean[] lamps = br.readLine().replace(" ", "").chars().mapToObj(c -> c == '1').toArray(Boolean[]::new);
        int studentCnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < studentCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int location = Integer.parseInt(st.nextToken());

            if(gender == 1) {
                doMale(lamps, location);
            } else {
                doFemale(lamps, location);
            }
        }

        for(int i = 0; i < lamps.length; i++) {
            boolean isOverLine = (i+1) % 20 == 0;  // 20의 배수인 경우 개행

            sb.append(lamps[i] ? 1 : 0)
              .append(isOverLine ? "" : " ")  // 20개일 때 뒤의 공백 제거
              .append(isOverLine ? System.lineSeparator() : "");
        }

        System.out.println(sb.toString().strip());
    }

    public void doFemale(Boolean[] lamps, int location) {
        lamps[location - 1] = !lamps[location - 1];  // 현재 전구는 무조건 반전(On/Off)
        int l = location - 2;  // location 이 0부터 시작하지 않아서 -2
        int r = location;  // -2(left) -1(Cur loc) 0(right) 현재 인덱스 상태

        while(0 <= l && r < lamps.length) {
            if(lamps[l] == lamps[r]) {
                lamps[l] = !lamps[l--];  // l-- 로 왼쪽 이동
                lamps[r] = !lamps[r++];  // r++ 로 오른쪽 이동
            } else {
                break;
            }
        }
    }

    public void doMale(Boolean[] lamps, int location) {
        int tmp = location - 1;

        while(lamps.length > tmp) {
            lamps[tmp] = !lamps[tmp];
            tmp += location;  // location 만큼 증가(배수)
        }
    }
}