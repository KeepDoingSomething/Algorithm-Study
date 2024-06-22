public class Lv2_87946 {
    static int answer;
    static boolean[] visit;

    public static void DFS(int depth, int k, int[][] dungeons){
        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && dungeons[i][0] <= k){
                visit[i] = true;

                DFS(depth + 1, k - dungeons[i][1], dungeons);

                visit[i] = false;
            }
        }

        answer = Math.max(depth, answer);
    }

    public int solution(int k, int[][] dungeons) {
        answer = -1;

        visit = new boolean[dungeons.length];

        DFS(0, k, dungeons);

        return answer;
    }

    public static void main(String[] args) {
        Lv2_87946 sol = new Lv2_87946();

        int[][] arr = {{80, 20}, {50, 40}, {30, 10}};

        System.out.println(sol.solution(80, arr));
    }
}
