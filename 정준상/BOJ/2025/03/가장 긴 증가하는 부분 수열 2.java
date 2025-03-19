import java.io.*;
import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-18
 * Runtime          :   516 ms
 * Memory           :   114.528 MB
 * Used algorithm   :   Binary search, Dynamic programming
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int len = 0;
        int[] arr = new int[N];

        for (int curr : numbers) {
            // curr 이 arr 에 존재하면 >= 0 인 index 가 반환됨.
            int index = Arrays.binarySearch(arr, 0, len, curr);
            
            // 문서에 의하면 index < 0 일 때, index = (-(insertion point) - 1) 를 만족하는 "삽입 지점" 을 찾을 수 있음.
            int insertionIndex = index < 0 ? -1 * (index + 1) : index;

            // 삽입하는 index 가 배열 길이보다 크면 확장.
            if (insertionIndex >= len)  {
                len++;
            }

            arr[insertionIndex] = curr;
        }

        System.out.println(len);
    }
}
