import java.util.Arrays;
import java.util.PriorityQueue;
/**
 *
 *   Author    : 전유진
 *   Date      : 2024.09.22(일)
 *   Algorithm : 정렬
 *
 */

public class 가장큰수 {
    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        solution(numbers);
    }

    private static String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }


        //내림차순
        Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        if(arr[0].equals("0")) {return "0";}
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();

    }
}
