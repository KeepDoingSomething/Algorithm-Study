package programmers.LV2_42746;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int[] numbers1 = {6, 10, 2};
        testCase.put("case1", new Object[]{numbers1});

        int[] numbers2 = {3, 30, 34, 5, 9};
        testCase.put("case2", new Object[]{numbers2});

        int[] numbers3 = {34, 3, 30};
        testCase.put("case3", new Object[]{numbers3});

        int[] numbers4 = {979, 97, 978, 81, 818, 817};
        testCase.put("case4", new Object[]{numbers4});

        int[] numbers5 = {998, 9, 999};
        testCase.put("case5", new Object[]{numbers5});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", "6210");
        resultCase.put("case2", "9534330");
        resultCase.put("case3", "34330");
        resultCase.put("case4", "9799797881881817");
        resultCase.put("case5", "9999998");
        return resultCase;
    }

}
