package programmers.LV2_388353;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        String[] storage1 = {"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] requests1 = {"A", "BB", "A"};
        testCase.put("case1", new Object[]{storage1, requests1});

        String[] storage2 = {"HAH", "HBH", "HHH", "HAH", "HBH"};
        String[] requests2 = {"C", "B", "B", "B", "B", "H"};
        testCase.put("case2", new Object[]{storage2, requests2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        resultCase.put("case1", 11);
        resultCase.put("case2", 4);

        return resultCase;
    }

}
