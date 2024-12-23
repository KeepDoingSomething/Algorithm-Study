package programmers.LV2_138476;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int k1 = 6;
        int[] tangerine1 = {1, 3, 2, 5, 4, 5, 2, 3};
        testCase.put("case1", new Object[]{k1, tangerine1});

        int k2 = 4;
        int[] tangerine2 = {1, 3, 2, 5, 4, 5, 2, 3};
        testCase.put("case2", new Object[]{k2, tangerine2});

        int k3 = 2;
        int[] tangerine3 = {1, 1, 1, 1, 2, 2, 2, 3};
        testCase.put("case3", new Object[]{k3, tangerine3});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 3);
        resultCase.put("case2", 2);
        resultCase.put("case3", 1);
        return resultCase;
    }

}
