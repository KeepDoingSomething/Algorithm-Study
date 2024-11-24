package programmers.LV2_43165;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{new int[]{1, 1, 1, 1, 1}, 3});
        testCase.put("case2", new Object[]{new int[]{4, 1, 2, 1}, 4});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 5);
        resultCase.put("case2", 2);
        return resultCase;
    }

}
