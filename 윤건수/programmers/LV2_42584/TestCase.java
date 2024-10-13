package programmers.LV2_42584;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int[] prices1 = {1, 2, 3, 2, 3};
        testCase.put("case1", new Object[]{prices1});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", new int[]{4, 3, 1, 1, 0});
        return resultCase;
    }

}
