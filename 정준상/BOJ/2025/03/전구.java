import java.io.*;
import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-18
 * Runtime          :   376 ms
 * Memory           :   25.332 MB
 * Used algorithm   :   Binary search, Dynamic programming
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final Map<Integer, Integer> switchToLight = new HashMap<>();
    private static final Map<Integer, Integer> lightToSwitch = new HashMap<>();

    private static int[] init() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] switchPose = getArr();
        int[] lightPose = getArr();
        for (int i = 0; i < N; i++) {
            switchToLight.put(lightPose[i], i + 1);
            lightToSwitch.put(i + 1, lightPose[i]);
        }

        // 스위치 인덱스에 따른 전구 위치로 변환 (LIS 준비)
        // 0 번 인덱스엔 4 번 인덱스 전구가 위치
        // 1 번 인덱스엔 0 번 인덱스 전구가 위치
        return Arrays.stream(switchPose)
                .map(switchToLight::get)
                .toArray();
    }

    private static int[] getArr() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        int[] arr = init();

        int[] dp = new int[arr.length];
        int len = 0;

        List<int[]> history = new ArrayList<>(arr.length);
        for (int val : arr) {
            int index = Arrays.binarySearch(dp, 0, len, val);
            int insertPoint = index < 0 ? -1 * (index + 1) : index;

            if (insertPoint >= len) {
                len++;
            }

            dp[insertPoint] = val;
            history.add(new int[] {insertPoint, val});
        }

        // LIS 역추적
        Collections.reverse(history);
        int[] answer = new int[len];

        while (!history.isEmpty()) {
            int[] hist = history.remove(0);
            int index = hist[0];
            int val = hist[1];

            if (index != len - 1)   {
                continue;
            }

            // 스위치 번호로 변환
            answer[--len] = lightToSwitch.get(val);
        }

        Arrays.sort(answer);

        System.out.println(answer.length);
        for (int val : answer) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
