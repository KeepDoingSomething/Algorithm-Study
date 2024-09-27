package programmers.LV3_60061;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int n1 = 5;
        int[][] build_frame1 = {
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {2, 1, 0, 1},
                {2, 2, 1, 1},
                {5, 0, 0, 1},
                {5, 1, 0, 1},
                {4, 2, 1, 1},
                {3, 2, 1, 1}
        };

        testCase.put("case1", new Object[]{n1, build_frame1});

        int n2 = 5;
        int[][] build_frame2 = {
                {0, 0, 0, 1},
                {2, 0, 0, 1},
                {4, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {2, 1, 1, 1},
                {3, 1, 1, 1},
                {2, 0, 0, 0},
                {1, 1, 1, 0},
                {2, 2, 0, 1}
        };
        testCase.put("case2", new Object[]{n2, build_frame2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        int[][] result1 = {
                {1, 0, 0},
                {1, 1, 1},
                {2, 1, 0},
                {2, 2, 1},
                {3, 2, 1},
                {4, 2, 1},
                {5, 0, 0},
                {5, 1, 0}
        };
        resultCase.put("case1", result1);

        int[][] result2 = {{0, 0, 0}, {0, 1, 1}, {1, 1, 1}, {2, 1, 1}, {3, 1, 1}, {4, 0, 0}};
        resultCase.put("case2", result2);
        return resultCase;
    }

}
