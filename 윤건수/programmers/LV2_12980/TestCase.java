package programmers.LV2_12980;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{5});
        testCase.put("case2", new Object[]{6});
        testCase.put("case3", new Object[]{5000});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 2);
        resultCase.put("case2", 2);
        resultCase.put("case3", 5);
        return resultCase;
    }

}
