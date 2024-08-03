import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public interface Problem<P>{

    Problem setAnswer(Object answer);
    HashMap<String, P> getInputCase();
    HashMap<String, Object> getResultCase();
    Object solve(P parameter) throws Exception;

    default void test() {

        try {
            // Result 저장
            Map<String, Object> resultCase = getResultCase();
            boolean isSuccess = true;

            Map<String, P> inputCase = getInputCase();
            for (String caseKey : inputCase.keySet()) {
                P input = inputCase.get(caseKey);

                System.out.println("테스트 실행 Input");
                println(input);

                long startTime = System.nanoTime();
                Object testResult = solve(input);
                long endTime = System.nanoTime();

                println("실행시간: " + (endTime-startTime)/1_000_000.00 +"ms");
                System.out.println();

                Object expectResult = resultCase.get(caseKey);

                boolean isCaseSuccess;
                if(NestedArrayUtil.isNestedArray(expectResult)){
                    isCaseSuccess = NestedArrayUtil.compareNestedArrays(expectResult, testResult);
                }else if(expectResult.getClass().isArray()){
                    isCaseSuccess = this.compareArray(expectResult, testResult);
                }else{
                    isCaseSuccess = expectResult.equals(testResult);
                }


                isSuccess = isSuccess && isCaseSuccess;

                println("정답");
                println(expectResult);
                System.out.println();

                println("결과");
                println(testResult);
                System.out.println();

                if(isCaseSuccess){
                    passPrintln("Case 통과");
                }else{
                    failPrintln("Case 실패");
                }

                System.out.println();
                println("=======================================================================================");
            }

            if(isSuccess){
                passPrintln("모든 테스트가 통과하였습니다.");
                System.out.println();
            }else{
                failPrintln("************** 실패한 테스트가 있습니다. 결과를 확인해주세요 **************");
                // 실패시 소리
                Toolkit.getDefaultToolkit().beep();
                System.out.println();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    };

    private boolean compareArray(Object arr1, Object arr2) {
        boolean result = true;
        for(int i = 0; i < Array.getLength(arr1); i++){
            Object arr1Element = Array.get(arr1, i);
            Object arr2Element = Array.get(arr2, i);
            result = result && arr1Element.equals(arr2Element);
        }
        return result;
    }

    private void passPrintln(String str){
        // console 색상
        String reset = "\u001B[0m";
        String green = "\u001B[32m";
        println(green + str + reset);
    }

    private void failPrintln(String str){
        // console 색상
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        println(red + str + reset);
    }

    default void println(Object input){
        if(input instanceof File) {
            try{
                FileInputStream inputFileStream = new FileInputStream((File)input);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputFileStream));
                StringBuilder fileString = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    fileString.append(line).append(System.lineSeparator());
                }
                System.out.print(fileString);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else if (input.getClass().isArray()) {
            printArray(input);
        } else {
            System.out.print(input);
        }

        System.out.println();
    }

    default void printArray(Object input){
        int length = Array.getLength(input);
        Object[] array = new Object[length];
        IntStream.range(0, length).forEach(i ->
                array[i] = Array.get(input, i)
        );

        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            Object value = array[i];
            if(value.getClass().isArray()){
                printArray(value);
            }else{
                System.out.print(value);
            }
            if(i != array.length - 1) System.out.print(", ");
        }

        System.out.print("]");
    }

}