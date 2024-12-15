package baekjoon.G4_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        boolean[][] visited = new boolean[n][n];
        int answer = 0;

        answer = dfs(n, -1, 0, visited);
        return answer;
    }

    public static int dfs(int n, int row, int col, boolean[][] visited){
        if(row == n - 1){
            return 1;
        }

        int result = 0;
        int nextRow = row + 1;
        for(int i = 0; i < n; i++){
            if(isValidPosition(n, nextRow, i, visited)){
                visited[nextRow][i] = true;
                result += dfs(n, nextRow, i, visited);
                visited[nextRow][i] = false;
            }
        }

        return result;
    }

    public static boolean isValidPosition(int n, int row, int col, boolean[][] visited){
        for(int i = 0; i < n; i++){
            if(row < n && visited[row][i] || visited[i][col]) return false;
            if(col - row + i < n && col - row + i >= 0 && visited[i][col - row + i]) return false;
            if(col + row - i < n && col + row - i >= 0 && visited[i][col + row - i]) return false;
        }

        return true;
    }
}
