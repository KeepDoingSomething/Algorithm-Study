package lang.Object.solution;

/**
 * Author    : HojipKim
 * Date      : 2024.06.20(Tue)
 * Runtime   : 0.05 ms
 * Memory    : 78.1 MB
 * Algorithm : 완전탐색, DFS
 */
class Solution {

    private static int answer;
    private boolean[] isVisited;

    public int solution(int k, int[][] dungeons) {

        isVisited = new boolean[dungeons.length];

        DFS(0, dungeons, k);

        return answer;
    }

    public void DFS(int depth, int[][] dungeons, int k){

        for (int i = 0; i < dungeons.length; i++) {
            if(!isVisited[i] && dungeons[i][0] <= k){
                isVisited[i] = true;
                DFS(depth + 1 , dungeons, k-dungeons[i][1]);
                isVisited[i] = false;
            }
        }
        answer = Math.max(answer, depth);

    }

}

class Main {
    public static void main(String[] args) {
        Solution question = new Solution();
        int answer = question.solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}});
        System.out.println(answer);
    }
}