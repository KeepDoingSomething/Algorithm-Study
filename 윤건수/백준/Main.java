package 백준;

import 백준.S4_3986.S4_3986;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws Exception {
        // 퇴사 (Silver 3)
        // https://www.acmicpc.net/problem/14501
        // test(new S3_14501());

        // 요세푸스 (Silver 4)
        // https://www.acmicpc.net/problem/1158
        // test(new S4_1158());

        // 암기왕 (Silver 4)
        // https://www.acmicpc.net/problem/2776
        // test(new S4_2776());


        // 좋은단어 (Silver 4)
        // https://www.acmicpc.net/problem/3986
        test(new S4_3986());
    }

    private static void test(Object problem) throws Exception {
        URL classDir = problem.getClass().getResource("");
        File[] files = new File(classDir.toURI()).listFiles();

        // Result 저장
        Map<String, String> result = new HashMap();
        Arrays.stream(files).filter((file) -> file.getName().startsWith("result")).forEach(file -> {
            String fileName = file.getName();
            String testSeq = fileName.substring(fileName.indexOf("result") + "result".length(), fileName.lastIndexOf("."));
            StringBuilder resultSb = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = "";
                while((line = br.readLine()) != null){
                    resultSb.append(line).append(System.lineSeparator());
                }

                result.put(testSeq, resultSb.toString().trim());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        AtomicBoolean isSuccess = new AtomicBoolean();
        isSuccess.set(true);

        // Input 실행
        Arrays.stream(files).forEach(file -> {
            String fileName = file.getName();
            boolean isInput = fileName.startsWith("input");
            if(isInput){
                String testSeq = fileName.substring(fileName.indexOf("input") + "input".length(), fileName.lastIndexOf("."));
                System.out.println("** " + testSeq + "번 테스트 실행 **");
                try {
                    long startTime = System.nanoTime();
                    String testResult
                            = (String) problem.getClass()
                            .getDeclaredMethod("solution", InputStream.class)
                            .invoke(problem, new FileInputStream(file));
                    long endTime = System.nanoTime();
                    System.out.println("실행시간: " + (endTime-startTime)/1_000_000.00 +"ms");
                    System.out.println();

                    String expectResult = result.get(testSeq);
                    System.out.println("정답");
                    System.out.println(expectResult);
                    System.out.println();

                    System.out.println("결과");
                    System.out.println(testResult);
                    System.out.println();

                    if(testResult.equals(expectResult)){
                        System.out.println(testSeq + "번 테스트 성공");
                        isSuccess.set(isSuccess.get());
                    }else{
                        System.out.println(testSeq + "번 테스트 실패");
                        isSuccess.set(false);
                    }

                } catch (IllegalAccessException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    System.out.println("solution 메서드가 없습니다");
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                System.out.println("=======================================================================================");
            }
        });

        if(isSuccess.get()){
            System.out.println("모든 테스트가 통과하였습니다.");
        }else{
            System.out.println("************** 실패한 테스트가 있습니다. 결과를 확인해주세요 **************");
        }
    }

}
