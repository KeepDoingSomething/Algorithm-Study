package programmers.LV2_389479;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int[] players1 = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m1 = 3;
        int k1 = 5;
        testCase.put("case1", new Object[]{players1, m1, k1});

        int[] players2 = {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0};
        int m2 = 5;
        int k2 = 1;
        testCase.put("case2", new Object[]{players2, m2, k2});

        int[] players3 = {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int m3 = 1;
        int k3 = 1;
        testCase.put("case3", new Object[]{players3, m3, k3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        resultCase.put("case1", 7);
        resultCase.put("case2", 11);
        resultCase.put("case3", 12);

        return resultCase;
    }

}
