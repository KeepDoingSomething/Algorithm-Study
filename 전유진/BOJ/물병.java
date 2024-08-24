import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 물병 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int count = 0;
        if (N <= K) { // 더 구매할 필요 없음
            System.out.println(0);
            return;
        }
        int buy = 0;
        while (true) {
            count = 0;
            int copyN = N;
            if (copyN % 2 == 1) { // 홀수 라면
                count += 1;
            }
            copyN /= 2;
            if (count <= K) {
                break;
            }
            N += 1;
            buy += 1;
        }
        System.out.println(buy);

    }

}
