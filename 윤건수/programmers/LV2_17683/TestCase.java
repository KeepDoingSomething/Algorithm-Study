package programmers.LV2_17683;

import java.util.HashMap;

public class TestCase {

    public HashMap<String, Object[]> getInput(){
        HashMap<String, Object[]> testCase = new HashMap<>();

        String m1 = "ABCDEFG";
        String[] musicinfos1 = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        testCase.put("case1", new Object[]{m1, musicinfos1});

        String m2 = "CC#BCC#BCC#BCC#B";
        String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        testCase.put("case2", new Object[]{m2, musicinfos2});

        String m3 = "ABC";
        String[] musicinfos3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        testCase.put("case3", new Object[]{m3, musicinfos3});

        String m4 = "CC#BCC#BCC#";
        String[] musicinfos4 = {"03:00,03:08,FOO,CC#B"};
        testCase.put("case4", new Object[]{m4, musicinfos4});

        String m5 = "ABC";
        String[] musicinfos5 = {"12:00,12:14,HELLO,CDEFGAB", "12:00,12:15,HELLOLL,CDEFGAB"};
        testCase.put("case5", new Object[]{m5, musicinfos5});

        String m6 = "ABC";
        String[] musicinfos6 = {"12:00,12:14,HELLO,CDEFGAB", "12:00,12:14,HELLOLL,CDEFGAB"};
        testCase.put("case6", new Object[]{m6, musicinfos6});


        return testCase;
    }

    public HashMap<String, Object> getResult(){
        HashMap<String, Object> resultCase = new HashMap<>();
        resultCase.put("case1", "HELLO");
        resultCase.put("case2", "FOO");
        resultCase.put("case3", "WORLD");
        resultCase.put("case4", "FOO");
        resultCase.put("case5", "HELLOLL");
        resultCase.put("case6", "HELLO");
        return resultCase;
    }

}
