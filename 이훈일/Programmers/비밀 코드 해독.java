class Solution {
    static int N, count;
    static int[][] Q;
    static int[] result, answer;
    
    public static int solution(int n, int[][] q, int[] ans) {
        N = n;
        Q = new int[q.length][5];
        for(int i = 0; i < q.length; i++) {
            for(int j = 0; j < 5; j++) {
                Q[i][j] = q[i][j];
            }
        }
        
        answer = new int[ans.length];
        for(int i = 0; i < ans.length; i++) {
            answer[i] = ans[i];
        }
        
        result = new int[5];
        count = 0;
        comb(0, 1);
        
        return count;
    }
    
    public static void comb(int depth, int start) {
        if(depth == 5) {
            if(isRight()) {
                count++;
            }
            return;
        }
        
        for(int i = start; i <= N; i++) {
            result[depth] = i;
            comb(depth+1, i+1);
        }
    }
    
    public static boolean isRight() {
        for(int i = 0; i < Q.length; i++) {
            int[] cur = Q[i];
            int cnt = 0;
            
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(result[j] == cur[k]) {
                        cnt++;
                    }
                }
            }

            if(cnt != answer[i]) {
                return false;
            }
        }
        return true;
    }
}
