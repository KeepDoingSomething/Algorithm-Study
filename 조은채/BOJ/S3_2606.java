import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S3_2606 {
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visit;
    static int answer = 0;

    public static void BFS(int computer){
        Queue<Integer> q = new LinkedList<>();
        q.offer(computer);
        visit[computer] = true;

        while (!q.isEmpty()){
            int tmp = q.poll();

            for (int a : list.get(tmp)) {
                if (!visit[a]){
                    BFS(a);

                    visit[a] = true;
                    answer++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1];
        list = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }

        int connect = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 0; i < connect; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        BFS(1);

        System.out.println(answer);
    }
}
