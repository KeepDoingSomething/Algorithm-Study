package programmers.LV2_42586;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        testCase.put("case1", new Object[]{progresses1, speeds1});

        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        testCase.put("case2", new Object[]{progresses2, speeds2});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        int[] result1 = {2, 1};
        resultCase.put("case1", result1);

        int[] result2 = {1, 3, 2};
        resultCase.put("case2", result2);
        return resultCase;
    }

}
