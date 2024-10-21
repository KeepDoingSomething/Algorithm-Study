package baekjoon.G4_1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        int numsCnt = Integer.parseInt(line1[0]);
        int goalSum = Integer.parseInt(line1[1]);
        String[] nums = br.readLine().split(" ");
        int left = 0;
        int right = 0;

        final int MAX = 100001;
        int result = MAX;
        int sum = Integer.parseInt(nums[0]);

        while(left <= right && left <= numsCnt - 1){
            if(sum >= goalSum){
                result = Math.min(right - left +1, result);
                sum -= Integer.parseInt(nums[left]);
                left++;
            }else{
                right++;
                if(right > numsCnt - 1) break;
                sum += Integer.parseInt(nums[right]);
            }
        }

        System.out.println(result == MAX ? 0 : result);

    }

}