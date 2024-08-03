package programmers.LV2_42587;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int[] priorities1 = {2, 1, 3, 2};
        testCase.put("case1", new Object[]{priorities1, 2});

        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        testCase.put("case2", new Object[]{priorities2, 0});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 1);
        resultCase.put("case2", 5);
        return resultCase;
    }

}
