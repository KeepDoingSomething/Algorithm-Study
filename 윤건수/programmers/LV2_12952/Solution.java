package programmers.LV2_12952;

public class Solution {

    boolean[][] visited;
    int n;
    int answer;

    public int solution(int n) {
        this.visited = new boolean[n][n];
        this.n = n;
        this.answer = 0;

        dfs(-1, 0);
        return answer;
    }

    public void dfs(int row, int col){
        if(row == n - 1){
            answer++;
            return;
        }

        int nextRow = row + 1;
        for(int i = 0; i < n; i++){
            if(isValidPosition(nextRow, i)){
                visited[nextRow][i] = true;
                dfs(nextRow, i);
                visited[nextRow][i] = false;
            }
        }
    }

    public boolean isValidPosition(int row, int col){
        for(int i = 0; i < n; i++){
            if(row < n && visited[row][i] || visited[i][col]) return false;
            if(col - row + i < n && col - row + i >= 0 && visited[i][col - row + i]) return false;
            if(col + row - i < n && col + row - i >= 0 && visited[i][col + row - i]) return false;
        }

        return true;
    }
}