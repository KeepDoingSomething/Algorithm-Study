package programmers.LV2_49993;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        testCase.put("case1", new Object[]{"CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 2);
        return resultCase;
    }

}
