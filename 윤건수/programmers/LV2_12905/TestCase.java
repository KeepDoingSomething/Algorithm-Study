package programmers.LV2_12905;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int[][] board1 = {{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        testCase.put("case1", new Object[]{board1});

        int[][] board2 = {{0, 0, 1, 1}, {1, 1, 1, 1}};
        testCase.put("case2", new Object[]{board2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 9);
        resultCase.put("case2", 4);
        return resultCase;
    }

}
