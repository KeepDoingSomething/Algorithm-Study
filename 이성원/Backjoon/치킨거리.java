import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static boolean visit[][];
    static int chicken[][];
    static int N;
    static int M;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N][N];
        chicken = new int[M][2]; //

        for(int i = 0;i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j <N; j++) {
                map[i][j]  = Integer.parseInt(st.nextToken());
            }
        }


        combination(0, 0);

        System.out.println(min);

    }

    public static void combination(int start, int depth) {
        if (depth == M) {
            int cityDistance = 0; // 도시의 치킨 거리 합산 변수

            // 모든 집에 대해 치킨 거리를 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) { // 집인 경우만 처리
                        int minDistance = Integer.MAX_VALUE;

                        // 선택된 M개의 치킨집 중 가장 가까운 거리 찾기
                        for (int k = 0; k < M; k++) {
                            int c1 = chicken[k][0];
                            int c2 = chicken[k][1];
                            int distance = Math.abs(i - c1) + Math.abs(j - c2);
                            minDistance = Math.min(minDistance, distance);
                        }

                        // 도시 치킨 거리에 더하기
                        cityDistance += minDistance;
                    }
                }
            }

            // 최소 도시 치킨 거리 갱신
            min = Math.min(min, cityDistance);
            return;
        }

        // 조합 생성: 2차원 배열을 1차원으로 변환
        for (int i = start; i < N * N; i++) {
            int r = i / N; // 행
            int c = i % N; // 열

            if (!visit[r][c] && map[r][c] == 2) { // 치킨집인 경우만 선택
                visit[r][c] = true;
                chicken[depth][0] = r;
                chicken[depth][1] = c;

                combination(i + 1, depth + 1); // 다음 조합 선택
                visit[r][c] = false; // 백트래킹
            }
        }
    }

}