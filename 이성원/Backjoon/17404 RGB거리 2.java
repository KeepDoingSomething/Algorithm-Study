import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public final static int INF = 100000;
    static int answer = INF;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());



        //각 집의 색깔별 최솟값
        int dp[][] = new int[N+1][3];

        int aw[][] = new int[N+1][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j =0; j < 3; j++) {
                aw[i+1][j] = Integer.parseInt(st.nextToken());
            }
        }


        //첫번째 색깔 선택
        for(int k = 0; k < 3; k++) {


            for(int i =0;i < 3; i++) {  //첫번째 집 색깔 선택 선택 안한 집은 최솟값을 구하기 위해 무한대로 초기화
                if(i==k) dp[1][i] = aw[1][i];
                else dp[1][i] = INF;
            }


            for(int i = 2; i <= N; i++) { //두번째 집 부터 반복문으로 체크 첫번째 집과 색깔이 겹치지 않아야함
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + aw[i][0];

                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + aw[i][1];

                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + aw[i][2];
            }


            for(int i = 0; i < 3; i++) { //마지막 집 즉 집들의 색깔을 다 칠한 집들의 최솟값을 구함 하지만 여기서 중요한 점은 첫번째 집이랑 색깔이 겹치면 안되므로 한번 검증 단계
                if(i != k) answer = Math.min(answer, dp[N][i]);
            }

        }

        System.out.println(answer);


    }




}
