package programmers.LV2_169199;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        String[] board1 = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        testCase.put("case1", new Object[]{board1});
        String[] board2 = {".D.R", "....", ".G..", "...D"};
        testCase.put("case2", new Object[]{board2});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 7);
        resultCase.put("case2", -1);
        return resultCase;
    }

}
