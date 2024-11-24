/**
 * Author    : Kang San Ah
 * Date      : 2024.09.16(Mon)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : Array
 */

import java.util.Arrays;

public class 가장큰수 {
    public String solution(int[] numbers) {
        String[] stringArr = new String[numbers.length];

        for (int i  = 0 ; i < numbers.length; i ++){
            stringArr[i] = String.valueOf(numbers[i]); // stringArr = ["6" , "10" , "2"]
        }

        Arrays.sort(stringArr, (o1, o2) -> (o2+o1).compareTo(o1+o2)); // ["6" , "2" , "10"]

        if (stringArr[0].equals("0")) return "0"; // 조건

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < stringArr.length; i++){
            sb.append(stringArr[i]); // "6210"
        }

        return sb.toString(); // 문자열 변환

    }
}
