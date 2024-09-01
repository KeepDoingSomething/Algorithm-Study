package programmers.LV2_118867;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int[] queue11 = {3, 2, 7, 2};
        int[] queue21 = {4, 6, 5, 1};
        testCase.put("case1", new Object[]{queue11, queue21});

        int[] queue12 = {1, 2, 1, 2};
        int[] queue22 = {1, 10, 1, 2};
        testCase.put("case2", new Object[]{queue12, queue22});

        int[] queue13 = {1, 1};
        int[] queue23 = {1, 5};
        testCase.put("case3", new Object[]{queue13, queue23});

        int[] queue14 = {1, 2};
        int[] queue24 = {2, 1};
        testCase.put("case4", new Object[]{queue14, queue24});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 2);
        resultCase.put("case2", 7);
        resultCase.put("case3", -1);
        resultCase.put("case4", 0);
        return resultCase;
    }

}
