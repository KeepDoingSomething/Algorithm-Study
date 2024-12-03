package programmers.LV2_72411;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        String[] orders1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2, 3, 4};
        testCase.put("case1", new Object[]{orders1, course1});

        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2, 3, 5};
        testCase.put("case2", new Object[]{orders2, course2});

        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2, 3, 4};
        testCase.put("case3", new Object[]{orders3, course3});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();

        String[] return1 = {"AC", "ACDE", "BCFG", "CDE"};
        resultCase.put("case1", return1);

        String[] return2 = {"ACD", "AD", "ADE", "CD", "XYZ"};
        resultCase.put("case2", return2);

        String[] return3 = {"WX", "XY"};
        resultCase.put("case3", return3);
        return resultCase;
    }

}
