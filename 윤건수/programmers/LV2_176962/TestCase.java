package programmers.LV2_176962;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        String[][] plans1 = {
                {"korean", "11:40", "30"},
                {"english", "12:10", "20"},
                {"math", "12:30", "40"}
        };

        String[][] plans2 = {
                {"science", "12:40", "50"},
                {"music", "12:20", "40"},
                {"history", "14:00", "30"},
                {"computer", "12:30", "100"}
        };

        String[][] plans3 = {
                {"aaa", "12:00", "20"},
                {"bbb", "12:10", "30"},
                {"ccc", "12:40", "10"}
        };

        testCase.put("case1", new Object[]{plans1});
        testCase.put("case2", new Object[]{plans2});
        testCase.put("case3", new Object[]{plans3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        String[] result1 = {"korean", "english", "math"};
        String[] result2 = {"science", "history", "computer", "music"};
        String[] result3 = {"bbb", "ccc", "aaa"};

        resultCase.put("case1", result1);
        resultCase.put("case2", result2);
        resultCase.put("case3", result3);

        return resultCase;
    }

}
