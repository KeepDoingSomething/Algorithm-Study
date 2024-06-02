package programmers.LV3_81303;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        int n1 = 8;
        int k1 = 2;
        String[] cmd1 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        testCase.put("case1", new Object[]{n1, k1, cmd1});

        int n2 = 8;
        int k2 = 2;
        String[] cmd2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        testCase.put("case2", new Object[]{n2, k2, cmd2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        String return1 = "OOOOXOOO";
        resultCase.put("case1", return1);

        String return2 = "OOXOXOOO";
        resultCase.put("case2", return2);
        return resultCase;
    }

}
