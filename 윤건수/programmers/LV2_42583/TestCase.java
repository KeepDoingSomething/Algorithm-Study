package programmers.LV2_42583;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int bridge_length1 = 2;
        int weight1 = 10;
        int[] truck_weights1 = {7, 4, 5, 6};
        testCase.put("case1", new Object[]{bridge_length1, weight1, truck_weights1});

        int bridge_length2 = 100;
        int weight2 = 100;
        int[] truck_weights2 = {10};
        testCase.put("case2", new Object[]{bridge_length2, weight2, truck_weights2});

        int bridge_length3 = 100;
        int weight3 = 100;
        int[] truck_weights3 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        testCase.put("case3", new Object[]{bridge_length3, weight3, truck_weights3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 8);
        resultCase.put("case2", 101);
        resultCase.put("case3", 110);
        return resultCase;
    }

}
