package baekjoon.G2_1033;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static ArrayList<Ratio>[] cocktail;
    static long[] result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st;

        long lcm = 1; // 최소 공배수
        int n = Integer.parseInt(br.readLine());
        cocktail = new ArrayList[n];
        visited = new boolean[n];
        result = new long[n];

        for (int i = 0; i < n; i++) {
            cocktail[i] = new ArrayList<Ratio>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            cocktail[a].add(new Ratio(b, p, q));
            cocktail[b].add(new Ratio(a, q, p));

            lcm *= p * q / gcd(p, q);
        }
        result[0] = lcm;
        dfs(0);
        long gcd = lcm;

        for (int i = 0; i < n; i++) {
            gcd = gcd(gcd, result[i]);
        }

        for (int i = 0; i < n; i++) {
            result[i] /= gcd;
            bw.write(result[i] + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        } else {
            long mod = a % b;
            return gcd(b, mod);
        }
    }

    public static void dfs(int a) {
        visited[a] = true;
        for (Ratio ratio : cocktail[a]) {
            int next = ratio.b;
            if (!visited[next]) {
                result[next] = result[a] * ratio.q / ratio.p;
                dfs(next);
            }
        }
    }
}

class Ratio {

    int b;
    int p;
    int q;

    Ratio(int b, int p, int q) {
        this.b = b;
        this.p = p;
        this.q = q;
    }

}
