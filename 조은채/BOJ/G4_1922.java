import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class G4_1922 {
    static int[] unf;

    public static int Find(int v){
        if (v == unf[v]){
            return v;
        }else {
            return unf[v] = Find(unf[v]);
        }
    }

    public static void Union(int a, int b){
        int fa = Find(a);
        int fb = Find(b);

        if (fa != fb){
            unf[fa] = fb;
        }
    }

    static class Edge implements Comparable<Edge>{
        int v1, v2, cost;

        Edge(int v1, int v2, int cost){
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        unf = new int[N + 1];

        ArrayList<Edge> list = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            unf[i] = i;
        }

        StringTokenizer st;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new Edge(v1, v2, cost));
        }

        int answer = 0;

        Collections.sort(list);

        for (Edge ob : list) {
            int fv1 = Find(ob.v1);
            int fv2 = Find(ob.v2);

            if (fv1 != fv2){
                answer += ob.cost;

                Union(ob.v1, ob.v2);
            }
        }

        System.out.println(answer);
    }
}
