package programmers.LV2_12946;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object> getInput(){
        HashMap<String, Object> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{2});
        testCase.put("case2", new Object[]{3});
        testCase.put("case3", new Object[]{4});
        testCase.put("case4", new Object[]{6});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        int[][] result1 = {{1, 2}, {1, 3}, {2, 3}};
        resultCase.put("case1", result1);

        int[][] result2 = {{1, 3}, {1, 2}, {3, 2}, {1, 3}, {2, 1}, {2, 3}, {1, 3}};
        resultCase.put("case2", result2);

        int[][] result3 = {{1, 2}, {1, 3}, {2, 3}, {1, 2}, {3, 1}, {3, 2}, {1, 2}, {1, 3}, {2, 3}, {2, 1}, {3, 1}, {2, 3}, {1, 2}, {1, 3}, {2, 3}};
        resultCase.put("case3", result3);

        int[][] result4 = {{1,2},{1,3},{2,3},{1,2},{3,1},{3,2},{1,2},{1,3},{2,3},{2,1},{3,1},{2,3},{1,2},{1,3},{2,3},{1,2},{3,1},{3,2},{1,2},{3,1},{2,3},{2,1},{3,1},{3,2},{1,2},{1,3},{2,3},{1,2},{3,1},{3,2},{1,2},{1,3},{2,3},{2,1},{3,1},{2,3},{1,2},{1,3},{2,3},{2,1},{3,1},{3,2},{1,2},{3,1},{2,3},{2,1},{3,1},{2,3},{1,2},{1,3},{2,3},{1,2},{3,1},{3,2},{1,2},{1,3},{2,3},{2,1},{3,1},{2,3},{1,2},{1,3},{2,3}};
        resultCase.put("case4", result4);
        return resultCase;
    }

}
