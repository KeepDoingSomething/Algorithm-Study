package programmers.LV2_12987;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int param11 = 5;
        int[][] param21 = {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}};
        int param31 = 3;
        testCase.put("case1", new Object[]{param11, param21, param31});

        int param12 = 6;
        int[][] param22 = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        int param32 = 4;
        testCase.put("case2", new Object[]{param12, param22, param32});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 4);
        resultCase.put("case2", 4);
        return resultCase;
    }

}
