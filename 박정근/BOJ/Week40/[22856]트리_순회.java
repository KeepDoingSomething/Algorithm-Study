/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.30(Sun)
 * Runtime   : 320ms
 * Memory    : 42432KB
 * Algorithm : Implementation
 */

// >> 첫 번째 풀이 ( Implementation )
// 구현으로 풀이했습니다. (Solve Time : 0h 21m)

// 문제에서 주어진 유사 중위 순회는, **루트에서 오른쪽 자식으로 계속 진행하다가, 오른쪽 자식이 존재하지 않는 노드가 순회의 끝 노드**입니다.
// 왼쪽 자식을 방문한다면 나중에 오른쪽 자식을 방문하기 위해 다시 해당 노드로 돌아와야 하지만, 오른쪽 자식을 방문한다면 더이상 그 노드에 방문하지 않아도 된다는 뜻입니다.
// 오른쪽 자식이 존재하지 않을 때 까지 이동 횟수를 센 다음. `(전체 노드 개수 - 1) * 2 - 이동 횟수` 로 답을 냅니다. (`(전체 노드 개수 - 1) * 2`는, 루트를 제외한 모든 노드가 2회 이동한 상황을 의미합니다.)
// + 그래서, 제 풀이에서 왼쪽 자식 입력은 저장할 필요가 없습니다.

import java.io.*;
import java.util.*;

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] rightChild = new int[n+1];
        int a, b, c;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            rightChild[a] = c;
        }
        
        int nownode = 1; // root
        int cnt = 0;
        while (true) {
            if (rightChild[nownode] == -1) break;
            else nownode = rightChild[nownode];

            cnt += 1;
        }

        System.out.println((n - 1) * 2 - cnt);
    }
}
