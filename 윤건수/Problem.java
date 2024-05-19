import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public interface Problem<P, R>{

    Problem<P, R> setAnswer(Object answer);
    HashMap<String, P> getInputCase();
    HashMap<String, R> getResultCase();
    R solve(P parameter) throws Exception;

    default void test() {
        try {
            // Result 저장
            Map<String, R> resultCase = getResultCase();
            boolean isSuccess = true;

            Map<String, P> inputCase = getInputCase();
            for (String caseKey : inputCase.keySet()) {
                P input = inputCase.get(caseKey);

                System.out.print("테스트 실행 Case: ");
                println(input);

                long startTime = System.nanoTime();
                R testResult = solve(input);
                long endTime = System.nanoTime();

                println("실행시간: " + (endTime-startTime)/1_000_000.00 +"ms");
                System.out.println();

                R expectResult = resultCase.get(caseKey);
                isSuccess = isSuccess && expectResult.equals(testResult);

                println("정답");
                println(expectResult);
                System.out.println();

                println("결과");
                println(testResult);
                System.out.println();
                println("=======================================================================================");
            }

            if(isSuccess){
                println("모든 테스트가 통과하였습니다.");
                System.out.println();
            }else{
                println("************** 실패한 테스트가 있습니다. 결과를 확인해주세요 **************");
                System.out.println();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    };

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
                System.out.println(fileString);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else if (input.getClass().isArray()) {
            int length = Array.getLength(input);
            Object[] array = new Object[length];
            IntStream.range(0, length).forEach(i ->
                    array[i] = Array.get(input, i)
            );
            System.out.println(Arrays.toString(array));
        } else {
            System.out.println(input);
        }
    }

}