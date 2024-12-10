/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.10(Tue)
 * Runtime   : 736ms
 * Memory    : 265460KB
 * Algorithm : Union Find
 */

// >> 첫 번째 풀이 ( Union Find )
// 유니온 파인드로 풀이했습니다. (Solve Time : 0h 9m)
// 저번 주 코드가 살짝 도움이 되었네요. 같은 그룹의 점을 이으려고 한다면 ans 를 처리합니다.
// find() 로 parent 배열을 동기화하고, union() 로 같은 그룹인지의 여부를 알 수 있습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class UnionFind {
    int[] parent;
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int a) {
        // Union Find : 부모 노드를 스택으로 찾으며, 나머지 노드 모두 부모를 동기화하는 함수
        Stack<Integer> s = new Stack<>();

        while (a != parent[a]) {
            s.push(a);
            a = parent[a];
        }

        while (!s.isEmpty()) parent[s.pop()] = a;

        return a;
    }

    public boolean union(int a, int b) {
        // Union Find : a와 b 를 같은 그룹으로 잇는 함수
        a = find(a);
        b = find(b);

        parent[b] = a;
        return a == b;
    }
}

class Main {
	static public void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        UnionFind uf = new UnionFind(n); // 유니온 파인드 클래스

        int ans = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // ans 가 0일 때 같은 그룹을 이으려고 한다면, 정답은 i + 1 번째 차례이다.
            // 추가적인 연산을 제외하고자, Short-Circuit Evaluation 의도가 살짝 있는 식입니다.
            if (ans == 0 && uf.union(a, b)) ans = i + 1;
        }

        System.out.println(ans);
    }
}
