import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        // 값 기준 내림차순 정렬, 값이 같으면 key 기준 내림차순
        list.sort((a, b) -> {
            int cmp = Integer.compare(a.getKey(), b.getKey()); // key 기준 오름차순 정렬
            return (cmp != 0) ? cmp : Integer.compare(b.getValue(), a.getValue()); // key가 같다면, value 기준 내림차순 정렬
        });

        int[] bag = new int[K];
        int sum = 0;

        // 가방 크기 입력
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);  // 가방 크기 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int j = 0;

        // 각 가방 크기마다 가능한 물건을 선택
        for (int i = 0; i < K; i++) {
            // 가방 크기 bag[i]보다 작은 물건들을 pq에 넣음
            while (j < N && list.get(j).getKey() <= bag[i]) {
                pq.add(list.get(j).getValue());
                j++;
            }

            // 최대 가치의 물건을 꺼내서 합산
            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }

        System.out.println(sum);
    }
}