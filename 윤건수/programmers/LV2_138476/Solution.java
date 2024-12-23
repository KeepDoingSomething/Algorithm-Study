package programmers.LV2_138476;

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> quantityOfSizePerTangerine = new HashMap<>();

        for(int sizeOfTangerine : tangerine){
            quantityOfSizePerTangerine.compute(sizeOfTangerine, (key, value) -> value == null ? 1 : value + 1);
        }

        List<Integer> sameSizeValues = quantityOfSizePerTangerine.values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int indexPerSize = 0;
        int total = 0;

        while(total<k){
            total += sameSizeValues.get(indexPerSize);
            indexPerSize++;
        }

        return indexPerSize;
    }
}