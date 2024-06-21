/**
 *  * Author    : Jeon Yu Jin
 *  * Date      : 2024.06.21(Fri)
 *  * Runtime   :
 *  * Memory    :
 *  * Algorithm : DFS
 */

public class 피로도 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {
                {80,20},{50,40},{30,10}
        };


        solution(k, dungeons);
    }

    static boolean[] visited;

    private static int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];

        dfs(0,k,dungeons);
        System.out.println("res = " + res);
        return res;
    }

    static int res = 0;

    public static void dfs(int stage, int k, int[][] dungeons){

        res = Math.max(res,stage);

        for(int i = 0; i < dungeons.length; i++){
            if(visited[i] == false && k >= dungeons[i][0]){
                visited[i] = true;
                dfs(stage+1,k-dungeons[i][1],dungeons);
                visited[i] = false;
            }
        }
    }
}
