public class N_Queen {
    public static void main(String[] args) {
        int n = 4;
        solution(n);
    }

    static int[] board;
    static int answer;

    private static void solution(int n) {
        board = new int[n];
        bac(0, n);
    }

    private static void bac(int depth, int n) {
        if (depth == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            board[depth] = i;
            if (valid(depth)) {
                bac(depth + 1, n);
            }
        }
    }

    private static boolean valid(int i) {
        for (int j = 0; j < i; j++) {
            if(board[i] == board[j]) return false; //직선
            if(Math.abs(i-j) == Math.abs(board[i]-board[j])) return false; //대각선
        }
        return true;
    }
}
