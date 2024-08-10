package programmers.LV2_42883;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{"1924", 2});
        testCase.put("case2", new Object[]{"1231234", 3});
        testCase.put("case3", new Object[]{"4177252841", 4});
        testCase.put("case4", new Object[]{"99991", 4});
        testCase.put("case5", new Object[]{"9999999999999999999991", 1});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", "94");
        resultCase.put("case2", "3234");
        resultCase.put("case3", "775841");
        resultCase.put("case4", "9");
        resultCase.put("case5", "999999999999999999999");
        return resultCase;
    }

}
