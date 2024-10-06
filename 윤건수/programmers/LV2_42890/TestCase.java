package programmers.LV2_42890;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        String[][] relation1 = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };

        String[][] relation2 = {
                {"100", "a"},
                {"100", "b"}
        };

        String[][] relation3 = {
                {"100", "a", "가", "A", "1"},
                {"200", "b", "나", "B", "2"}
        };

        String[][] relation4 = {
                {"100", "a", "가", "A", "1"},
                {"100", "a", "가", "A", "1"}
        };

        String[][] relation5 = {
                {"11", "223", "33"},
                {"112", "23", "33"},
                {"112", "23", "3"},
                {"11", "23", "33"}
        };

        String[][] relation6 = {
                {"a", "1", "aaa", "c", "ng"},
                {"b", "1", "bbb", "c", "g"},
                {"c", "1", "aaa", "d", "ng"},
                {"d", "2", "bbb", "d", "ng"}
        };

        testCase.put("case1", new Object[]{relation1});
        testCase.put("case2", new Object[]{relation2});
        testCase.put("case3", new Object[]{relation3});
        testCase.put("case4", new Object[]{relation4});
        testCase.put("case5", new Object[]{relation5});
        testCase.put("case6", new Object[]{relation6});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 2);
        resultCase.put("case2", 1);
        resultCase.put("case3", 5);
        resultCase.put("case4", 0);
        resultCase.put("case5", 1);
        resultCase.put("case6", 3);
        return resultCase;
    }

}
