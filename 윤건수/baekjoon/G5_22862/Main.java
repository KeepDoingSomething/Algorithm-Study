package baekjoon.G5_22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int arrLength = Integer.parseInt(params[0]);
        int totalDeleteCnt = Integer.parseInt(params[1]);

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int deleteCnt = 0;
        int evenLength = 0;
        int maxEvenLength = 0;
        int left = 0;
        for (int right = 0; right < arrLength; right++) {
            int numRight = arr[right];
            boolean rightEven = numRight % 2 == 0;
            if(rightEven){
                evenLength++;
            }else{
                deleteCnt++;
                while(deleteCnt > totalDeleteCnt){
                    int numLeft = arr[left];
                    boolean leftOdd = numLeft % 2 != 0;
                    if(leftOdd){
                        deleteCnt--;
                    }else{
                        evenLength--;
                    }
                    left++;
                }
            }

            maxEvenLength = Math.max(evenLength, maxEvenLength);
        }

        System.out.println(maxEvenLength);
    }

}
