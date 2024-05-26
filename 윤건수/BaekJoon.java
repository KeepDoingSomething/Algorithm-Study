import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

public class BaekJoon implements Problem<File, String>{

    Object answer;
    File[] testFiles;

    public Problem<File, String> setAnswer(Object answer) {
        this.answer = answer;
        URL classDir = answer.getClass().getResource("");
        try{
            testFiles = new File(classDir.toURI()).listFiles();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return this;
    }

    @Override
    public HashMap<String, File> getInputCase() {
        HashMap<String, File> testCase = new HashMap<>();
        Arrays.stream(testFiles)
                .filter((file) -> file.getName().startsWith("input"))
                .forEach(file -> {
                    String fileName = file.getName();
                    String testSeq = fileName.substring(fileName.indexOf("input") + "input".length(), fileName.lastIndexOf("."));
                    testCase.put(testSeq, file);
                });
        return testCase;
    }

    @Override
    public HashMap<String, String> getResultCase() {
        HashMap<String, String> result = new HashMap<>();
        try {
            Arrays.stream(testFiles)
                    .filter((file) -> file.getName().startsWith("result"))
                    .forEach(file -> {
                        String fileName = file.getName();
                        String testSeq = fileName.substring(fileName.indexOf("result") + "result".length(), fileName.lastIndexOf("."));
                        StringBuilder resultSb = new StringBuilder();
                        try {
                            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            String line = "";
                            while ((line = br.readLine()) != null) {
                                resultSb.append(line).append(System.lineSeparator());
                            }

                            result.put(testSeq, resultSb.toString().trim());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public String solve(File file) throws Exception {
        InputStream parameter = new FileInputStream(file);
        System.setIn(parameter);
        ByteArrayOutputStream resultOutputStream = new ByteArrayOutputStream();
        PrintStream resultSave = new PrintStream(resultOutputStream);
        PrintStream resultConsole = System.out;

        System.setOut(resultSave);
        answer.getClass()
                .getDeclaredMethod("main", String[].class)
                .invoke(answer, (Object) null);
        System.out.flush();

        String testResult = resultOutputStream.toString().trim(); // System.out.println() 으로 정답 입력시 개행문자 제거
        resultOutputStream.close();
        parameter.close();
        System.setOut(resultConsole);
        return testResult;
    }

}
