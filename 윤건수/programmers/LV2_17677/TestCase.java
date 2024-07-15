package programmers.LV2_17677;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{"FRANCE", "french"});
        testCase.put("case2", new Object[]{"handshake", "shake hands"});
        testCase.put("case3", new Object[]{"aa1+aa2", "AAAA12"});
        testCase.put("case4", new Object[]{"E=M*C^2", "e=m*c^2"});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 16384);
        resultCase.put("case2", 65536);
        resultCase.put("case3", 43690);
        resultCase.put("case4", 65536);
        return resultCase;
    }

}
