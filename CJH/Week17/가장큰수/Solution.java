package programmers.level2.가장큰수;

import java.util.Arrays;

public class Solution {

    public String solution(int[] numbers) {
        String[] array = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        Arrays.sort(array, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        // 맨 앞의 값이 0이라면 
        if (array[0].equals("0")) return "0";

        return String.join("", array);
    }
}
