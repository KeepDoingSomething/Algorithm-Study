package programmers.LV2_340212;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int[] diffs1 = {1, 5, 3};
        int[] times1 = {2, 4, 7};
        long limit1 = 30;
        testCase.put("case1", new Object[]{diffs1, times1, limit1});

        int[] diffs2 = {1, 4, 4, 2};
        int[] times2 = {6, 3, 8, 2};
        long limit2 = 59;
        testCase.put("case2", new Object[]{diffs2, times2, limit2});

        int[] diffs3 = {1, 328, 467, 209, 54};
        int[] times3 = {2, 7, 1, 4, 3};
        long limit3 = 1723;
        testCase.put("case3", new Object[]{diffs3, times3, limit3});

        int[] diffs4 = {1, 99999, 100000, 99995};
        int[] times4 = {9999, 9001, 9999, 9001};
        long limit4 = 3456789012L;
        testCase.put("case4", new Object[]{diffs4, times4, limit4});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        resultCase.put("case1", 3);
        resultCase.put("case2", 2);
        resultCase.put("case3", 294);
        resultCase.put("case4", 39354);

        return resultCase;
    }

}
