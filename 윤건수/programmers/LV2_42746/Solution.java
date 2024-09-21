package programmers.LV2_42746;

import java.util.*;
import java.util.stream.*;

public class Solution {

    static boolean isZero = true;

    public String solution(int[] numbers) {

        String result = Arrays
                .stream(numbers)
                .boxed()
                .sorted((a, b) -> {
                    if(a != 0 || b != 0) isZero = false;
                    String numberStr1 = String.valueOf(a);
                    String numberStr2 = String.valueOf(b);
                    return (numberStr2 + numberStr1).compareTo(numberStr1 + numberStr2);
                })
                .map(String::valueOf)
                .collect(Collectors.joining());

        return isZero ? "0" : result;
    }

}