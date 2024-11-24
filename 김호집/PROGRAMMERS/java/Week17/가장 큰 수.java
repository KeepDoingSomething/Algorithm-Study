/**
 * Author    : HojipKim
 * Date      : 2024.09.27(Fri)
 * Runtime   : 193.63 ms
 * Memory    : 130 MB
 * Algorithm : 정렬
 */

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        String[] arr = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String numToString = String.valueOf(numbers[i]);
            arr[i] = numToString;
        }

        Arrays.sort(arr, (o1, o2) -> (o2+o1).compareTo(o1+o2));

        if(arr[0].equals("0")){
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        for(String str : arr){
            sb.append(str);
        }

        return sb.toString();
    }
}