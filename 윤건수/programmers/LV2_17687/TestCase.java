package programmers.LV2_17687;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        testCase.put("case1", new Object[]{2, 4, 2, 1});
        testCase.put("case2", new Object[]{16, 16, 2, 1});
        testCase.put("case3", new Object[]{16, 16, 2, 2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", "0111");
        resultCase.put("case2", "02468ACE11111111");
        resultCase.put("case3", "13579BDF01234567");
        return resultCase;
    }

}
