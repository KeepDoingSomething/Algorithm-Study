package programmers.LV2_169198;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput() {
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{10, 10, 3, 7, new int[][]{{7, 7}, {2, 7}, {7, 3}}});
        return testCase;
    }

    public HashMap<String, Object> getResult() {
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", new int[]{52, 37, 116});
        return resultCase;
    }

}
