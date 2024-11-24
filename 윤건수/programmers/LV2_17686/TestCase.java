package programmers.LV2_17686;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        testCase.put("case1", new Object[]{files1});

        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        testCase.put("case2", new Object[]{files2});

        String[] files3 = {"muzi1.txt", "MUZI1.txt", "muzi001.txt", "muzi1.TXT"};
        testCase.put("case3", new Object[]{files3});

        String[] files4 = {"A0999", "A01", "A0001", "A9999."};
        testCase.put("case4", new Object[]{files4});

        String[] files5 = {"foo9.txt", "foo010bar020.zip", "F-15"};
        testCase.put("case5", new Object[]{files5});

        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", new String[]{"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"});
        resultCase.put("case2", new String[]{"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"});
        resultCase.put("case3", new String[]{"muzi1.txt", "MUZI1.txt", "muzi001.txt", "muzi1.TXT"});
        resultCase.put("case4", new String[]{"A01", "A0001", "A0999", "A9999."});
        resultCase.put("case5", new String[]{"F-15", "foo9.txt", "foo010bar020.zip"});
        return resultCase;
    }

}
