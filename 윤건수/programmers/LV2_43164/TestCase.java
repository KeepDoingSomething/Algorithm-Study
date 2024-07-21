package programmers.LV2_43164;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        String[][] tickets1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        testCase.put("case1", new Object[]{tickets1});

        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        testCase.put("case2", new Object[]{tickets2});

        String[][] tickets3 = {{"ICN", "BBB"}, {"BBB", "ICN"}, {"ICN", "AAA"}};
        testCase.put("case3", new Object[]{tickets3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", new String[]{"ICN", "JFK", "HND", "IAD"});
        resultCase.put("case2", new String[]{"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"});
        resultCase.put("case3", new String[]{"ICN", "BBB", "ICN", "AAA"});
        return resultCase;
    }

}
