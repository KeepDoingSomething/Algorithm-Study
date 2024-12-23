package programmers.LV2_340211;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int[][] points1 = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes1 = {{4, 2}, {1, 3}, {2, 4}};
        testCase.put("case1", new Object[]{points1, routes1});

        int[][] points2 = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes2 = {{4, 2}, {1, 3}, {4, 2}, {4, 3}};
        testCase.put("case2", new Object[]{points2, routes2});

        int[][] points3 = {{2, 2}, {3, 2}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes3 = {{2, 3, 4, 5}, {1, 3, 4, 5}};
        testCase.put("case3", new Object[]{points3, routes3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 1);
        resultCase.put("case2", 9);
        resultCase.put("case3", 0);
        return resultCase;
    }

}
