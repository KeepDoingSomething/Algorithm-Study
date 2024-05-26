package programmers.LV2_12899;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{1});
        testCase.put("case2", new Object[]{2});
        testCase.put("case3", new Object[]{3});
        testCase.put("case4", new Object[]{4});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", "1");
        resultCase.put("case2", "2");
        resultCase.put("case3", "4");
        resultCase.put("case4", "11");
        return resultCase;
    }

}
