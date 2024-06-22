package programmers.LV4_1843;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        String[] param1 = {"1", "-", "3", "+", "5", "-", "8"};
        testCase.put("case1", new Object[]{param1});

        String[] param2 = {"5", "-", "3", "+", "1", "+", "2", "-", "4"};
        testCase.put("case2", new Object[]{param2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 1);

        resultCase.put("case2", 3);
        return resultCase;
    }

}
