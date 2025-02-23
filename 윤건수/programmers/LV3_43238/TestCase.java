package programmers.LV3_43238;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int n1 = 6;
        int[] times1 = {7, 10};
        testCase.put("case1", new Object[]{n1, times1});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        resultCase.put("case1", 28L);

        return resultCase;
    }

}
