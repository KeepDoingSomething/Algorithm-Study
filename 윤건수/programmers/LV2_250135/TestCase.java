package programmers.LV2_250135;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int h1 = 0;
        int m1 = 5;
        int s1 = 30;
        int h2 = 0;
        int m2 = 7;
        int s2 = 0;
        testCase.put("case1", new Object[]{h1, m1, s1, h2, m2, s2});

        h1 = 12;
        m1 = 0;
        s1 = 0;
        h2 = 12;
        m2 = 0;
        s2 = 30;
        testCase.put("case2", new Object[]{h1, m1, s1, h2, m2, s2});

        h1 = 0;
        m1 = 6;
        s1 = 1;
        h2 = 0;
        m2 = 6;
        s2 = 6;
        testCase.put("case3", new Object[]{h1, m1, s1, h2, m2, s2});

        h1 = 11;
        m1 = 59;
        s1 = 30;
        h2 = 12;
        m2 = 0;
        s2 = 0;
        testCase.put("case4", new Object[]{h1, m1, s1, h2, m2, s2});

        h1 = 11;
        m1 = 58;
        s1 = 59;
        h2 = 11;
        m2 = 59;
        s2 = 0;
        testCase.put("case5", new Object[]{h1, m1, s1, h2, m2, s2});

        h1 = 1;
        m1 = 5;
        s1 = 5;
        h2 = 1;
        m2 = 5;
        s2 = 6;
        testCase.put("case6", new Object[]{h1, m1, s1, h2, m2, s2});

        h1 = 0;
        m1 = 0;
        s1 = 0;
        h2 = 23;
        m2 = 59;
        s2 = 59;
        testCase.put("case7", new Object[]{h1, m1, s1, h2, m2, s2});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 2);
        resultCase.put("case2", 1);
        resultCase.put("case3", 0);
        resultCase.put("case4", 1);
        resultCase.put("case5", 1);
        resultCase.put("case6", 2);
        resultCase.put("case7", 2852);
        return resultCase;
    }

}
