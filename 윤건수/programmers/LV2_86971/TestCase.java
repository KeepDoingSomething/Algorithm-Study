package programmers.LV2_86971;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int n1 = 9;
        int[][] wires1 = {{1,3}, {2,3}, {3,4}, {4,5}, {4,6}, {4,7}, {7,8}, {7,9}};
        testCase.put("case1", new Object[]{n1, wires1});

        int n2 = 4;
        int[][] wires2 = {{1,2}, {2,3}, {3,4}};
        testCase.put("case2", new Object[]{n2, wires2});

        int n3 = 7;
        int[][] wires3 = {{1,2}, {2,7}, {3,7}, {3,4}, {4,5}, {6,7} };
        testCase.put("case3", new Object[]{n3, wires3});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 3);
        resultCase.put("case2", 0);
        resultCase.put("case3", 1);
        return resultCase;
    }

}
