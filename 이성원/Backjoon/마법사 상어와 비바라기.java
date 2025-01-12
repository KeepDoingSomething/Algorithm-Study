import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static boolean cloude[][];
    static boolean copyCloude[][];
    static int N;
    static int M;
    static int dx[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int dy[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        cloude = new boolean[N][N];
        copyCloude = new boolean[N][N];

        for(int i =0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        firstInit();
        for(int i =0 ; i< M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            moveCloude(d,c);

            diagonal();

            minusTwo();


        }
        int answer = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                answer += map[i][j];

            }
        }

        System.out.println(answer);

    }

    public static  void firstInit() {
        cloude[N-2][0] = true;
        cloude[N-2][1] = true;
        cloude[N-1][0] = true;
        cloude[N-1][1] = true;
    }

    public static void moveCloude(int d, int c) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(cloude[i][j]) {
                    int nextR = (i + (dx[d] + N) * c) % N;
                    int nextC = (j + (dy[d] + N) * c) % N;

                    map[nextR][nextC] ++;
                    cloude[i][j] = false;
                    copyCloude[nextR][nextC] = true;

                }
            }
        }
    }

    public static void diagonal() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(copyCloude[i][j]) {

                    for(int k = 2; k <=8; k+=2) {
                        int nextR = i + dx[k];
                        int nextC = j + dy[k];

                        if(nextR <= N-1 && nextR >=0 && nextC <=N-1 && nextC>=0 && map[nextR][nextC] !=0) {
                            map[i][j]++;
                        }
                    }

                }

            }
        }
    }

    public static void minusTwo() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!copyCloude[i][j] && map[i][j] >=2) {
                    cloude[i][j] = true;
                    map[i][j] -= 2;
                }
                else if(copyCloude[i][j]) {
                    copyCloude[i][j] = false;
                }
            }
        }

    }


}
