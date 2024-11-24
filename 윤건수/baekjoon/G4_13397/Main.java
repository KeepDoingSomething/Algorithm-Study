package baekjoon.G4_13397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static PriorityQueue<Integer> arrPointQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arrLength = Integer.parseInt(st.nextToken());
        int goalDivCount = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[arrLength];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arrLength; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        int pointMin = 0;
        int pointMax = max;
        int divCount = 1;
        int answer = max;
        while (pointMax >= pointMin) {
            divCount = 1;
            int checkPoint = (pointMax + pointMin) / 2;
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for(int i = 0; i < arrLength; i++){
                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i]);
                int point = max - min;
                if(point > checkPoint){
                    max = Integer.MIN_VALUE;
                    min = Integer.MAX_VALUE;
                    divCount++;
                    i--;
                }
            }

            if (divCount > goalDivCount) {
                pointMin = checkPoint + 1;
            }else {
                pointMax = checkPoint - 1;
                answer = checkPoint;
            }

        }

        System.out.println(answer);
    }

}
