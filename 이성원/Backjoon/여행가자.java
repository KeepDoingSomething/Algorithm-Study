import java.util.*;
import java.io.*;

public class Main {

    static int parents[];
    static boolean visit[];
    static int city[];

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 도시의 개수
        M = Integer.parseInt(br.readLine());  // 여행 계획에 포함된 도시 개수

        parents = new int[N+1];
        visit = new boolean[N+1];

        city = new int[M]; // 여행 계획에 속한 도시들

        // 부모 배열 초기화
        for(int i = 1; i <= N; i++){
            parents[i] = i;
        }

        // 도로 정보 처리 (연결된 도시들 union)
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                int road  = Integer.parseInt(st.nextToken());
                if(road == 1){
                    union(i, j);  // 연결된 도시들 union
                }
            }
        }

        // 여행 계획에 속한 도시들 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < M; i++){
            city[i] = Integer.parseInt(st.nextToken());
            max = Math.max(city[i], max);  // 여행 계획에서 가장 큰 도시 번호 기록
        }

        // 여행 계획에 속한 도시들이 모두 연결되어 있는지 확인
        if (checkConnected()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean checkConnected() {
        // 여행 계획에서 첫 번째 도시의 부모를 기준으로, 다른 도시들과 동일한 부모인지 확인
        int root = find(city[0]);

        for (int i = 1; i < M; i++) {
            if (find(city[i]) != root) {
                return false;  // 연결되어 있지 않으면 NO 반환
            }
        }

        return true;  // 모두 연결되어 있으면 YES
    }

    // 두 도시를 연결하는 함수 (union-find)
    public static boolean union(int x, int y){
        int newX = find(x);
        int newY = find(y);

        if(newX == newY) return false;  // 이미 연결되어 있으면 처리 안 함

        if(newX <= newY){
            parents[newY] = newX;
        } else {
            parents[newX] = newY;
        }

        return true;
    }

    // 부모를 찾는 함수
    public static int find(int k){
        if(parents[k] == k) return k;
        return find(parents[k]);  // 경로 압축
    }
}