package programmers.LV2_92342;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}});
        testCase.put("case2", new Object[]{1, new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
        testCase.put("case3", new Object[]{9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1}});
        testCase.put("case4", new Object[]{10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3}});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", new int[]{0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0});
        resultCase.put("case2", new int[]{-1});
        resultCase.put("case3", new int[]{1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0});
        resultCase.put("case4", new int[]{1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2});
        return resultCase;
    }

}
