package programmers.LV2_155651;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();
        String[][] book_time1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        testCase.put("case1", new Object[]{book_time1});

        String[][] book_time2 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
        testCase.put("case2", new Object[]{book_time2});

        String[][] book_time3 = {{"10:20", "12:30"},{"10:20", "12:30"},{"10:20", "12:30"}};
        testCase.put("case3", new Object[]{book_time3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", 3);
        resultCase.put("case2", 1);
        resultCase.put("case3", 3);
        return resultCase;
    }

}
