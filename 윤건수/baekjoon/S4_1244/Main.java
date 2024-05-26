package baekjoon.S4_1244;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 스위치 껴고크기 (Silver 4)
// https://www.acmicpc.net/problem/1244

public class Main {

    static int[] lamps;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lampNum = Integer.parseInt(br.readLine());
        lamps = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int studentNum = Integer.parseInt(br.readLine());

        String studentInfo;
        while((studentInfo = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(studentInfo);
            String gender = st.nextToken();
            String lampParam = st.nextToken();
            switch (gender){
                case "1":
                    changeLampsForMan(lampParam);
                    break;
                case "2":
                    changeLampsForWoman(lampParam);
                    break;
            }
        }

        StringBuilder result = new StringBuilder();
        int lineSeq = 20;
        for(int i = 0; i < lamps.length; i++){
            if(i != 0 && i % lineSeq == 0) {
                result.deleteCharAt(result.length() - 1);
                result.append(System.lineSeparator());
            }
            result.append(lamps[i]).append(" ");
        }

        result.deleteCharAt(result.length() - 1);

        System.out.println(result);
    }

    private static void changeLampsForMan(String numberStr){
        for(int i = 0; i < lamps.length; i++){
            int lampNum = i + 1;
            int number = Integer.parseInt(numberStr);
            if(lampNum % number == 0){
                toggleSwitch(i);
            }
        }
    }

    private static void changeLampsForWoman(String lampNum){
        int lampIdx = Integer.parseInt(lampNum) - 1;
        toggleSwitch(lampIdx);
        checkSideLamp(lampIdx, 1);
    }

    private static void checkSideLamp(int lampIdx, int gap){
        int left = lampIdx - gap;
        int right = lampIdx + gap;
        boolean isValidIdx = left >= 0 && right < lamps.length;
        if(isValidIdx && lamps[left] == lamps[right]){
            toggleSwitch(left);
            toggleSwitch(right);
            checkSideLamp(lampIdx, gap + 1);
        }
    }

    private static void toggleSwitch(int index){
        lamps[index] = lamps[index] == 1 ? 0 : 1;
    }

}
