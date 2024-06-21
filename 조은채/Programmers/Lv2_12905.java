public class Lv2_12905 {
    public int solution(int [][]board) {
        int answer = 0;

        int[][] dp = new int[board.length + 1][board[0].length + 1];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 1){
                    answer = 1;

                    dp[i][j] = 1;
                }
            }
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(dp[i][j] != 0){

                    int up = dp[i - 1][j];
                    int left = dp[i][j - 1];
                    int upLeft = dp[i - 1][j - 1];

                    dp[i][j] = Math.min(up, Math.min(left, upLeft)) + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        return answer * answer;
    }

    public static void main(String[] args) {
        Lv2_12905 sol = new Lv2_12905();

        int[][] arr = {{0,1,1,1}, {1,1,1,1}, {1,1,1,1}, {0,0,1,0}};

        System.out.println(sol.solution(arr));
    }
}
