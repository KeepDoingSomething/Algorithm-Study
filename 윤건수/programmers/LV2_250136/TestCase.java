package programmers.LV2_250136;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int[][] land1 = {{0, 0, 0, 1, 1, 1, 0, 0},{0, 0, 0, 0, 1, 1, 0, 0},{1, 1, 0, 0, 0, 1, 1, 0},{1, 1, 1, 0, 0, 0, 0, 0},{1, 1, 1, 0, 0, 0, 1, 1}};
        testCase.put("case1", new Object[]{land1});

        int[][] land2 = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
        testCase.put("case2", new Object[]{land2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 9);
        resultCase.put("case2", 16);
        return resultCase;
    }

}
