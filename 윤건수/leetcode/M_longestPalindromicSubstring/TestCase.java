package leetcode.M_longestPalindromicSubstring;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{"babad"});
        testCase.put("case2", new Object[]{"cbbd"});
        testCase.put("case3", new Object[]{"ac"});
        testCase.put("case4", new Object[]{"a"});
        testCase.put("case5", new Object[]{"bb"});
        testCase.put("case6", new Object[]{"abb"});
        testCase.put("case7", new Object[]{"aba"});
        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", "aba");
        resultCase.put("case2", "bb");
        resultCase.put("case3", "a");
        resultCase.put("case4", "a");
        resultCase.put("case5", "bb");
        resultCase.put("case6", "bb");
        resultCase.put("case7", "aba");
        return resultCase;
    }

}
