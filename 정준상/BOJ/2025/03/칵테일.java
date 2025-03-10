import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-04
 * Runtime          :   132 ms
 * Memory           :   16.452 MB
 * Used algorithm   :   Graph, Union find, LCM & GCD
 */
class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] parents;
    private static long[] values;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        parents = IntStream.range(0, N).toArray();
        values = new long[N];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // : a / b = p / q
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long p = Integer.parseInt(st.nextToken());
            long q = Integer.parseInt(st.nextToken());

            // gcd 안 나눠도 풀리지만 혹시 모를 overflow 방지
            long gcd = gcd(p, q);
            p /= gcd;
            q /= gcd;

            // 새로운 node 2 개 생성 --> 비율 맞추려고 고민 필요 X
            if (values[a] == values[b] && values[a] == 0) {
                values[a] = p;
                values[b] = q;
            }

            // 새로운 node 1 개 생성 
            // --> (존재하지 않는 node 값) & (존재하는 node 값) 비율 맞춰주면서 node 생성
            else if (values[a] == 0) {
                values[a] = p * values[b];
                multiplyGivenToGroup(b, q);     // 결국 values[b] 는 q * values[b] 되서 비율 맞춰짐
            }

            else if (values[b] == 0) {
                values[b] = q * values[a];
                multiplyGivenToGroup(a, p);
            }

            // 이미 존재하는 node 들에 대해 비율 맞춰줘야 됨
            else {
                long lcm = lcm(values[a], values[b]);
                long mulA = lcm / values[a];
                long mulB = lcm / values[b];

                // 일단 values[a], values[b] 같은 값 (lcm) 으로 맞춰줌
                // a, b 의 sub-node 간 비율 자동으로 맞춰짐
                multiplyGivenToGroup(a, mulA);
                multiplyGivenToGroup(b, mulB);

                // 주어진 비율로 다시 맞춰줌
                multiplyGivenToGroup(a, p);
                multiplyGivenToGroup(b, q);
            }

            int p1 = findParent(a);
            int p2 = findParent(b);
            parents[Math.max(p1, p2)] = Math.min(p1, p2);
        }

        // 나눠질 수 있는 걸로 최대한 나눠줌
        // gcd(a, b, c) = gcd(gcd(a, b), c) 임
        long gcd = Arrays.stream(values).reduce(values[0], Main::gcd);
        for (long val : values) {
            System.out.print(val / gcd + " ");
        }
        System.out.println();
    }

    // indexInGroup 와 같은 parent 를 가진 node 들 값을 *= mul
    private static void multiplyGivenToGroup(int indexInGroup, long mul) {
        if (mul > 1) {
            int parent = findParent(indexInGroup);
            IntStream.range(0, N)
                    .filter(i -> findParent(i) == parent)
                    .forEach(i -> values[i] *= mul);
        }
    }

    private static int findParent(int i) {
        return parents[i] = i == parents[i] ?
                parents[i] : findParent(parents[i]);
    }

    private static long lcm(long a, long b)    {
        long gcd = gcd(a, b);
        return (a / gcd) * (b / gcd) * gcd;
    }

    private static long gcd(long a, long b) {
        long max = Math.max(a, b);
        long min = Math.min(a, b);

        long mod;
        while ((mod = max % min) != 0)  {
            max = min;
            min = mod;
        }

        return min;
    }
}
