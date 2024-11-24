import java.util.*;
import java.io.*;
class Solution {
    static boolean[] v;
     public static int solution(int n, int[][] computers) {
        int answer = 0;

        v = new boolean[n];

        for (int i = 0; i < n; i++) {

            if (!v[i]) {

                Queue<Integer> q = new LinkedList<>();
                answer++;
                v[i] = true;
                q.add(i);

                while (!q.isEmpty()) {
                    int now = q.poll();

                    for (int j = 0; j < n; j++) {
                        if (!v[j] && computers[now][j] == 1) {
                            q.add(j);
                            v[j] = true;
                        }
                    }
                }
            }

        }

        return answer;
    }
}
