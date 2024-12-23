package baekjoon.G2_2637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int infoCnt = Integer.parseInt(br.readLine());

        int[] use = new int[target + 1];
        boolean[] isMaked = new boolean[target + 1];
        int[][] makeCount = new int[target + 1][target + 1];

        while (infoCnt-- > 0) {
            String[] info = br.readLine().split(" ");
            int product = Integer.parseInt(info[0]);
            int material = Integer.parseInt(info[1]);
            int count = Integer.parseInt(info[2]);

            makeCount[product][material] = count;
            use[material]++;
            isMaked[product] = true;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(target);

        int[] needCount = new int[target + 1];
        needCount[target] = 1;
        while (!queue.isEmpty()) {
            int curProduct = queue.poll();
            for (int next = 1; next <= target; next++) {
                if (makeCount[curProduct][next] > 0) {
                    needCount[next] += makeCount[curProduct][next] * needCount[curProduct];
                    use[next]--;

                    if (use[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }

        for (int i = 1; i <= target; i++) {
            if (!isMaked[i]) {
                System.out.println(i + " " + needCount[i]);
            }
        }
    }
}
