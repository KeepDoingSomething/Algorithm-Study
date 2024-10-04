package baekjoon.G4_13397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainFail {

    static PriorityQueue<Integer> arrPointQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arrSize = Integer.parseInt(st.nextToken());
        int divCount = Integer.parseInt(st.nextToken());
        int[] arr = new int[arrSize];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minOfMax = findPoint(arr, 0, arr.length - 1);

        Set<Integer> divPoints = new HashSet<>();
        while(divCount-- > 1){
            FindResult result = findBestHalf(arr, minOfMax);
            arr = result.arr;
            minOfMax = result.maxPoint;
            divPoints.add(result.anotherMaxPoint);
        }

        divPoints.add(minOfMax);
        Optional<Integer> answer = divPoints.stream().sorted().max(Comparator.comparingInt(i -> i));

        System.out.println(answer.get());
    }

    private static FindResult findBestHalf(int[] arr, int minOfMax) {
        int[] maxArr = arr;
        int anotherMax = minOfMax;

        for(int i = 1; i < arr.length; i++){
            int div1MaxValue = arr[0];
            int div1MinValue = arr[0];

            int div2MinValue = arr[i];
            int div2MaValue = arr[i];

            for(int j = 0; j < i; j++ ){
                div1MaxValue = Math.max(div1MaxValue, arr[j]);
                div1MinValue = Math.min(div1MinValue, arr[j]);
            }

            for(int j = i; j < arr.length; j++){
                div2MaValue = Math.max(div2MaValue, arr[j]);
                div2MinValue = Math.min(div2MinValue, arr[j]);
            }
            int div1Point = div1MaxValue - div1MinValue;
            int div2Point = div2MaValue - div2MinValue;

            if(div1Point >= div2Point && div1Point <= minOfMax){
                minOfMax = div1Point;
                anotherMax = div2Point;
                maxArr = Arrays.copyOfRange(arr, 0, i);
            }else if(div2Point > div1Point && div2Point <= minOfMax){
                minOfMax = div2Point;
                anotherMax = div1Point;
                maxArr = Arrays.copyOfRange(arr, i, arr.length);
            }
        }

        System.out.println(Arrays.toString(maxArr));

        return new FindResult(minOfMax, anotherMax, maxArr);
    }

    private static class FindResult{
        int maxPoint;
        int anotherMaxPoint;
        int[] arr;

        FindResult(int maxPoint, int anotherMaxPoint, int[] arr){
            this.maxPoint = maxPoint;
            this.anotherMaxPoint = anotherMaxPoint;
            this.arr = arr;
        }
    }

    public static int findPoint(int[] arr, int start, int end) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        return max - min;
    }

}
