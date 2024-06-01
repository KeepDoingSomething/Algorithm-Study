import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lv2_86971 {
    static int answer = Integer.MAX_VALUE;

    static ArrayList<ArrayList<Integer>> list;

    public void BFS(int index, int n, int[][] wires){
        list = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < wires.length; i++) {
            if (i == index){
                continue;
            }

            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        boolean[] check = new boolean[n + 1];
        check[1] = true;

        while (!q.isEmpty()){
            int tmp = q.poll();

            for (int a : list.get(tmp)) {
                if (!check[a]){
                    q.offer(a);
                    check[a] = true;
                }
            }
        }

        int a = 0, b = 0;
        for (int i = 1; i < check.length; i++) {
            if (check[i]){
                a++;
            }else {
                b++;
            }
        }

        answer = Math.min(answer, Math.abs(a - b));
    }

    public int solution(int n, int[][] wires) {
        for (int i = 0; i < wires.length; i++) {
            BFS(i, n, wires);
        }

        return answer;
    }

    public static void main(String[] args) {
        Lv2_86971 sol = new Lv2_86971();

        /*int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        System.out.println(sol.solution(9, wires));*/

        /*int[][] wires = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println(sol.solution(4, wires));*/

        int[][] wires = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};
        System.out.println(sol.solution(7, wires));
    }
}
