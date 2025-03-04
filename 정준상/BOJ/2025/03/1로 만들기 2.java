import java.io.*;
import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-04
 * Runtime          :   112 ms
 * Memory           :   22.056 MB
 * Used algorithm   :   BFS
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] table = new int[N + 1];
        Queue<Integer> q = new ArrayDeque<>(N);
        q.add(N);

        while (!q.isEmpty() && table[1] == 0)    {

            int curr = q.poll();

            int target;
            if (curr % 3 == 0)  {
                target = curr / 3;

                if (table[target] == 0)    {
                    table[target] = curr;
                    q.add(target);
                }
            }

            if (curr % 2 == 0)  {
                target = curr / 2;
                if (table[target] == 0)    {
                    table[target] = curr;
                    q.add(target);
                }
            }

            target = curr - 1;
            if (target >= 0 && table[target] == 0)    {
                table[target] = curr;
                q.add(target);
            }
        }

        Deque<Integer> ans = new ArrayDeque<>();
        ans.add(1);

        int tmp = 1;
        while (tmp != N)    {
            ans.add(table[tmp]);
            tmp = table[tmp];
        }

        System.out.println(ans.size() - 1);
        while (!ans.isEmpty()) {
            System.out.print(ans.pollLast() + " ");
        }
        System.out.println();
    }
}
