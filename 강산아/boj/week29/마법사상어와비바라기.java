/**
 * Author    : Kang San Ah
 * Date      : 2025.01.09(Thu)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 시뮬레이션
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와비바라기 {

    static int N, M, totalWater;
    static int[][] arr;
    static boolean[][] cloud, visited;
    static StringTokenizer st;

    static int[][] dis = {
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1}
    };

    public static void simulation(int d, int s) {
        visited = new boolean[N][N];
        boolean[][] newCloud = new boolean[N][N];

        // 1. 구름 이동
        for (int x = 0 ; x < N ; x ++) {
            for (int y = 0 ; y < N; y ++){
                if (cloud[x][y]){
                    // 모듈러 계산 (arr 확장 가능)
                    int nx = (x + dis[d][0]*s + N *100 ) % N;
                    int ny = (y + dis[d][1]*s + N *100 ) % N;

                    newCloud[nx][ny] = true;
                    //2. 물 증가
                    arr[nx][ny] ++;
                }
            }
        }
        //3. 구름 사라진다
        cloud = newCloud;

        //4. 물복사 버그
        for (int x = 0 ; x < N ; x++){
            for (int y = 0 ; y < N ; y++){
                if (cloud[x][y]){
                    visited[x][y] = true;
                    int waterCount = 0;

                    for (int i = 1 ; i < 8 ; i+=2){
                        int nx = x + dis[i][0];
                        int ny = y + dis[i][1];
                        if (nx >= 0 && ny >= 0 && nx < N && ny < N && arr[nx][ny] > 0){
                            waterCount ++;
                        }
                    }
                    arr[x][y] += waterCount;
                }
            }
        }

        //5. 새로운 구름 생성 , -2 적용
        newCloud = new boolean[N][N];
        for (int x = 0; x < N ; x++){
            for (int y = 0; y < N ; y++){
                if (!visited[x][y] && arr[x][y] >=2){
                    newCloud[x][y] = true;
                    arr[x][y] -= 2;
                }
            }
        }

        cloud = newCloud;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        cloud = new boolean[N][N];

        // 초기 배열 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 위치 설정
        cloud[N-1][0] = true;
        cloud[N-1][1] = true;
        cloud[N-2][0] = true;
        cloud[N-2][1] = true;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            simulation(d, s);
        }

        totalWater = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                totalWater += arr[i][j];
            }
        }

        System.out.println(totalWater);
    }
}
