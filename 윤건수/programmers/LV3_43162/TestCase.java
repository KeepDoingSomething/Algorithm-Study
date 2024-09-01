package programmers.LV3_43162;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int n1 = 3;
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        testCase.put("case1", new Object[]{n1, computers1});
        int n2 = 3;
        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        testCase.put("case2", new Object[]{n2, computers2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 2);

        resultCase.put("case2", 1);
        return resultCase;
    }

}
