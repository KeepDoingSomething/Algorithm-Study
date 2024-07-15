package programmers.LV2_150369;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int cap1 = 4;
        int n1 = 5;
        int[] deliveries1 = {1, 0, 3, 1, 2};
        int[] pickups1 = {0, 3, 0, 4, 0};
        testCase.put("case1", new Object[]{cap1, n1, deliveries1, pickups1});

        int cap2 = 2;
        int n2 = 7;
        int[] deliveries2 = {1, 0, 2, 0, 1, 0, 2};
        int[] pickups2 = {0, 2, 0, 1, 0, 2, 0};
        testCase.put("case2", new Object[]{cap2, n2, deliveries2, pickups2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 16L);
        resultCase.put("case2", 30L);
        return resultCase;
    }

}
