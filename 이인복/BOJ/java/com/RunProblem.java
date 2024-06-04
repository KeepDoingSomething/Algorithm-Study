package com;

import com.Week04.S3_1004.S3_1004;
import com.Week04.S3_2606.S3_2606;
import com.self.S1_2468.Q2468;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RunProblem {

    public static final String INPUT_DIR = "in";
    public static final String OUTPUT_DIR = "out";
    public static final String RESULT_FILE_NAME = "result.txt";
    public static int passCnt = 0;

    public static StringBuilder sb = new StringBuilder();

    public static Comparator<File> fileNameNaturalOrder(Function<File, String> key) {
        return Comparator.comparing(
                file -> {
                    String name = key.apply(file).replaceAll("[^0-9]", "");
                    return name.isEmpty() ? 0 : Integer.parseInt(name);
                }
        );
    }

    public static void main(String[] args) throws Exception {
        Solution sol = new S3_2606();

        List<File> inputFiles = Arrays.stream(new File(sol.getClass().getResource(INPUT_DIR).toURI()).listFiles())
                                      .sorted(fileNameNaturalOrder(File::getName))
                                      .collect(Collectors.toList());
        List<File> outputFiles = Arrays.stream(new File(sol.getClass().getResource(OUTPUT_DIR).toURI()).listFiles())
                                       .sorted(fileNameNaturalOrder(File::getName))
                                       .collect(Collectors.toList());
        List<Integer> failedList = new ArrayList<>();
        // 특정 문제만 풀고 싶을 때, 문제 번호 입력
        Set<Integer> fixedNumbers = new HashSet<>(List.of());

        for(int i = 0; i < inputFiles.size(); i++) {
            File inFile = inputFiles.get(i);
            File outFile = outputFiles.get(i);
            int fileNumber = i + 1;

            if(!fixedNumbers.isEmpty() && !fixedNumbers.contains(fileNumber)) {
                continue;
            }

            sb.append("========== [Test Case(" + (fileNumber) + ") Result] ==========\n")
              .append("Input File : " + inFile.getName() + "\n")
              .append("Output File: " + outFile.getName() + "\n");

            if(!run(sol, inFile.getPath(), outFile.getPath())) {
                failedList.add(fileNumber);
            }
        }
        sb.append("===========================================\n")
          .append("Total: " + inputFiles.size() + "개\n")
          .append("Pass : " + passCnt + "개\n")
          .append("Failed List: ");

        for(Integer fileNumber : failedList) {
            sb.append(fileNumber + " ");
        }

        sb.append(failedList.isEmpty() ? "All Clear" : "...");

        System.out.println(sb);
    }

    public static boolean run(Solution sol, String inFileName, String outFileName) throws Exception {
        InputStream originalInputStream = System.in;  // 원본 System.in 백업
        PrintStream originalOut = System.out;  // 원본 System.out 백업

        try(FileInputStream inputFileStream = new FileInputStream(inFileName);
             PrintStream resultOutStream = new PrintStream(new FileOutputStream(RESULT_FILE_NAME))) {

            System.setIn(inputFileStream);  // 파일 입력을 System.in 으로 리디렉션
            System.setOut(resultOutStream);  // 임시 파일에 출력

            Long srtTime = System.currentTimeMillis();
            sol.solution();

            sb.append("Execution Time: " + (System.currentTimeMillis() - srtTime) + " ms")
              .append(System.lineSeparator());

            resultOutStream.flush();  // 출력 스트림 강제 비우기
        } finally {
            System.setIn(originalInputStream);  // 원래의 System.in 복원
            System.setOut(originalOut);  // 원래의 System.out 복원
        }

        try(BufferedReader expectedReader = new BufferedReader(new FileReader(outFileName));
             BufferedReader actualReader = new BufferedReader(new FileReader(RESULT_FILE_NAME))) {

            boolean isOk = true;
            String expected;
            String actual;

            while((expected = expectedReader.readLine()) != null) {
                actual = actualReader.readLine();

                if(actual == null || !expected.equals(actual)) {
                    isOk = false;
                }
            }

            if(actualReader.readLine() != null) {
                isOk = false;
            }

            sb.append("Result: ")
              .append(isOk ? "Pass" : "Fail")
              .append(System.lineSeparator());

            if(isOk) {
                passCnt++;
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        } finally {
            Files.delete(Paths.get(RESULT_FILE_NAME));
        }
    }
}