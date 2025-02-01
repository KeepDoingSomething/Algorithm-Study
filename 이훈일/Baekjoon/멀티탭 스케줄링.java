package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1700 {
    static int N, K, product, maxIndex;
    static List<Integer> graph;
    static HashSet<Integer> plugs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            graph.add(Integer.parseInt(st.nextToken()));
        }

        plugs = new HashSet<>();
        int count = 0;
        for(int i = 0; i < K; i++) {
            int num = graph.remove(0);

            if(plugs.contains(num)) {
                continue;
            }

            if(plugs.size() < N) {
                plugs.add(num);
                continue;
            }

            product = 0;
            maxIndex = -1;
            getNoUseProduct();

            plugs.remove(product);
            plugs.add(num);

            count++;
        }
        System.out.println(count);
    }

    public static void getNoUseProduct() {
        for(int plug : plugs) {
            if(!graph.contains(plug)) {
                product = plug;
                break;
            } else {
                int index = graph.indexOf(plug);
                if(index > maxIndex) {
                    maxIndex = index;
                    product = plug;
                }
            }
        }
    }
}
