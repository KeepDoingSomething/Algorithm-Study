package programmers.LV2_77485;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int[][] queries1 = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        testCase.put("case1", new Object[]{6, 6, queries1});

        int[][] queries2 = {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}};
        testCase.put("case2", new Object[]{3, 3, queries2});

        int[][] queries3 = {{1, 1, 100, 97}};
        testCase.put("case3", new Object[]{100, 97, queries3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        int[] result1 = {8, 10, 25};
        resultCase.put("case1", result1);

        int[] result2 = {1, 1, 5, 3};
        resultCase.put("case2", result2);

        int[] result3 = {1};
        resultCase.put("case3", result3);
        return resultCase;
    }

}
