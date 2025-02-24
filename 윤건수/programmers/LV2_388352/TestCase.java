package programmers.LV2_388352;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int n1 = 10;
        int[][] q1 = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int[] ans1 = {2, 3, 4, 3, 3};
        testCase.put("case1", new Object[]{n1, q1, ans1});

        int n2 = 15;
        int[][] q2 = {{2, 3, 9, 12, 13}, {1, 4, 6, 7, 9}, {1, 2, 8, 10, 12}, {6, 7, 11, 13, 15}, {1, 4, 10, 11, 14}};
        int[] ans2 = {2, 1, 3, 0, 1};
        testCase.put("case2", new Object[]{n2, q2, ans2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 3);
        resultCase.put("case2", 5);
        return resultCase;
    }

}
