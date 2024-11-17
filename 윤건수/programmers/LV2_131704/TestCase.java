package programmers.LV2_131704;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{new int[]{4, 3, 1, 2, 5}});
        testCase.put("case2", new Object[]{new int[]{5, 4, 3, 2, 1}});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 2);
        resultCase.put("case2", 5);
        return resultCase;
    }

}
