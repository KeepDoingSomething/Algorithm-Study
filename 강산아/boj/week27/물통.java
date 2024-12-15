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
        int C = volume[2] - A - B;
        int spaceA = volume[0] - A;
        int spaceB = volume[1] - B;
        int spaceC = volume[2] - C;

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

        StringBuilder sb = new StringBuilder();
        for(int i=volume[1]; i>=0; i--){
            if(visited[0][i]){
                sb.append(volume[2] - i).append(" ");
            }
        }
        System.out.println(sb);
    }

}
