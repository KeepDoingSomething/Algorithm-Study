package programmers.LV3_12927;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int n1 = 4;
        int[] works1 = {4, 3, 3};
        testCase.put("case1", new Object[]{n1, works1});

        int n2 = 1;
        int[] works2 = {2, 1, 2};
        testCase.put("case2", new Object[]{n2, works2});

        int n3 = 3;
        int[] works3 = {1, 1};
        testCase.put("case3", new Object[]{n3, works3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        resultCase.put("case1", 12);
        resultCase.put("case2", 6);
        resultCase.put("case3", 0);

        return resultCase;
    }

}
