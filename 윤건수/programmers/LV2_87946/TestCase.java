package programmers.LV2_87946;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        int totalEnergy = 80;
        int[][] dungeons = {{80, 20},{50, 30},{30, 10}};
        testCase.put("case1", new Object[]{totalEnergy, dungeons});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 3);
        return resultCase;
    }

}
