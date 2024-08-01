package programmers.LV2_42860;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{"JEROEN"});
        testCase.put("case2", new Object[]{"JAN"});
        testCase.put("case3", new Object[]{"BBBBAAAABA"});


        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 56);
        resultCase.put("case2", 23);
        resultCase.put("case3", 12);
        return resultCase;
    }

}
