package programmers.LV2_42839;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        String numbers1 = "17";
        testCase.put("case1", new Object[]{numbers1});

        String numbers2 = "011";
        testCase.put("case2", new Object[]{numbers2});

        String numbers3 = "143";
        testCase.put("case3", new Object[]{numbers3});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 3);
        resultCase.put("case2", 2);
        resultCase.put("case3", 6);
        return resultCase;
    }

}
