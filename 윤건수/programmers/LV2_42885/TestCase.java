package programmers.LV2_42885;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int[] people1 = {70, 50, 80, 50};
        int limit1 = 100;
        testCase.put("case1", new Object[]{people1, limit1});


        int[] people2 = {70, 80, 50};
        int limit2 = 100;
        testCase.put("case2", new Object[]{people2, limit2});


        int[] people3 = {1, 1, 1, 1, 1, 1};
        int limit3 = 10;
        testCase.put("case3", new Object[]{people3, limit3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 3);
        resultCase.put("case2", 3);
        resultCase.put("case3", 3);
        return resultCase;
    }

}
