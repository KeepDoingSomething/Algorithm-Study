import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[N][0] = Integer.parseInt(st.nextToken());
            arr[N][1] = Integer.parseInt(st.nextToken());
        }

        // 값 기준 내림차순 정렬, 값이 같으면 key 기준 내림차순
        Arrays.sort(arr, (o1, o2) -> {
            if(o1[0] == o2[0]){  //만약 key가 같은 경우는 value 기준 내림차순
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        int[] bag = new int[K];
        int sum = 0;

        // 가방 크기 입력
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);  // 가방 크기 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int bagLimit = 0;

        // 각 가방 크기마다 가능한 물건을 선택
        for (int i = 0; i < K; i++) {
            // 가방 크기 bag[i]보다 작은 물건들을 pq에 넣음
            while (bagLimit < N && arr[j][0] <= bag[i]) {
                pq.add(arr[j][1]);
                bagLimit++;
            }

            // 최대 가치의 물건을 꺼내서 합산
            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }

        System.out.println(sum);
    }
}