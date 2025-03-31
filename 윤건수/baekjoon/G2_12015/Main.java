package baekjoon.G2_12015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        int[] arr = stringLineToIntegerArray(br.readLine());
        int[] lisArr = new int[cnt];
        Arrays.fill(lisArr, Integer.MAX_VALUE);

        int result = 0;
        for (int num : arr) {
            int lisIdx = binarySearchForLIS(num, lisArr);
            lisArr[lisIdx] = num;
            result = Math.max(result, lisIdx + 1);
        }

        System.out.println(result);
    }

    private static int[] stringLineToIntegerArray(String readLine){
        return Arrays.stream(readLine.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static int binarySearchForLIS(int num, int[] lisArr) {
        int left = 0;
        int right = lisArr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            int target = lisArr[mid];
            if (target < num) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

}
