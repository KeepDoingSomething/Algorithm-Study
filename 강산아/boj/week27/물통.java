/**
 * Author    : Kang San Ah
 * Date      : 2024.12.15(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : dfs
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 물통 {

    static int volume[];
    static boolean visited[][];

    public static void dfs(int A, int B){
        int C = volume[2] - A - B;  // C 물통에서 A와 B 물통에 물을 붓고 남은 양
        int spaceA = volume[0] - A; // A 물통의 남은 공간
        int spaceB = volume[1] - B; // B 물통의 남은 공간
        int spaceC = volume[2] - C; // C 물통의 남은 공간

        if(visited[A][B]) { return;}
        visited[A][B] = true;


        if(A > 0){
            int BW = Math.min(spaceB, A);
            dfs(A - BW, B + BW);
            int CW = Math.min(spaceC, A);
            dfs(A - CW, B);
        }
        if(B > 0){
            int AW = Math.min(spaceA, B);
            dfs(A + AW, B - AW);
            int CW = Math.min(spaceC, B);
            dfs(A, B - CW);
        }
        if(C > 0){
            int AW = Math.min(spaceA, C);
            dfs(A + AW, B);
            int BW = Math.min(spaceB, C);
            dfs(A, B + BW);
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        volume = new int[3];

        for(int i=0; i<3; i++){
            volume[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[volume[0]+1][volume[1]+1];
        dfs(0, 0);

        // 오름차순 출력, A가 0인 배열에서 B가 큰 값부터 visited[0][B]가 true면 C에 있는 물의 양을 계산해서 출력
        StringBuilder sb = new StringBuilder();
        for(int i=volume[1]; i>=0; i--){
            if(visited[0][i]){
                sb.append(volume[2] - i).append(" ");
            }
        }
        System.out.println(sb);
    }

}
