/**
 * Author    : Park Jeong Geun
 * Date      : 2025.02.04(Tue)
 * Runtime   : 108ms
 * Memory    : 14280KB
 * Algorithm : Combinatorics
 */

// >> 첫 번째 풀이 ( 조합론 )
// 조합론 원리를 이용해 구현 및 풀이했습니다. (Solve Time : 1h 31m)

// 먼저, a와 z 를 이용해 만들 수 있는 문자열을 사전 순으로 나열해봤습니다.
// 원활한 설명을 위해, a가 N개 있는 문자열 안에 z를 M개 넣는다고 가정합니다. 이때 z의 위치는 맨 뒤 삽입 가능 공간부터 0입니다.

// Ex. (N, M) = (3, 3)
// (순서) / z의 위치 / 문자열
// (1) 0 0 0 / aaazzz
// (2) 1 0 0 / aazazz
// (3) 1 1 0 / aazzaz
// (4) 1 1 1 / aazzza 
// (5) 2 0 0 / azaazz
// (6) 2 1 0 / azazaz
// (7) 2 1 1 / azazza
// (8) 2 2 0 / azzaaz
// (9) 2 2 1 / azzaza
// (10) 2 2 2 / azzzaa
// (11) 3 0 0 / zaaazz
// (12) 3 1 0 / zaazaz
// (13) 3 1 1 / zaazza 
// (14) 3 2 0 / zazaaz
// (15) 3 2 1 / zazaza 
// (16) 3 2 2 / zazzaa 
// (17) 3 3 0 / zzaaaz
// (18) 3 3 1 / zzaaza 
// (19) 3 3 2 / zzazaa 
// (20) 3 3 3 / zzzaaa 

// (N, M) = (3, 3) 일 때, 찾은 규칙성은 다음과 같습니다. 
// 첫 번째 z의 위치가 바뀌기 위해선 각각 1, 3, 6, 10... 개의 문자열을 지나야 합니다.
// 두 번째 z의 위치가 바뀌기 위해선 각각 1, 2, 3 개의 문자열을 지나야 합니다. (단, 첫 번째 z의 위치를 넘지 않는다.)

// ...
// 1 1 1 / aazzza
// 2 0 0 / azaazz -> 첫 번째 z의 위치가 2가 됨. (앞 az는 고정되고 뒤 aazz 가 변한다. 지나야 하는 문자열 개수는 aazz로 만들 수 있는 문자열의 총 개수 (1 + 2 + 3))
//                ->    두 번째 z의 위치가 0이 됨. (앞 azaaz는 고정된다. 뒤에 z가 있는 경우는 1개)
// 2 1 0 / azazaz ->    두 번째 z의 위치가 1이 됨. (앞 azaz는 고정되고 뒤 az가 변한다. 지나야 하는 문자열 개수는 az로 만들 수 있는 문자열의 총 개수 (2))
// 2 1 1 / azazza
// 2 2 0 / azzaaz ->    두 번째 z의 위치가 2가 됨. (앞 azz는 고정되고 뒤 aaz가 변한다. 지나야 하는 문자열 개수는 aaz로 만들 수 있는 문자열의 총 개수 (3))
// 2 2 1 / azzaza
// 2 2 2 / azzzaa
// 3 0 0 / zaaazz -> 첫 번째 z의 위치가 3이 됨. (앞 z는 고정되고 뒤 aaazz 가 변한다. 지나야 하는 문자열 개수는 aaazz로 만들 수 있는 문자열의 총 개수 (1 + 2 + 3 + 4))
//                ->    두 번째 z의 위치가 0이 됨. (앞 zaaaz는 고정된다. 뒤에 z가 있는 경우는 1개)
// 3 1 0 / zaazaz ->    두 번째 z의 위치가 1이 됨. (앞 zaaz는 고정되고 뒤 az가 변한다. 지나야 하는 문자열 개수는 az로 만들 수 있는 문자열의 총 개수 (2))
// ...

// 즉, i (i >= 0)번째 z의 위치가 j가 되었을 때, 바뀌기 위해 지나야 하는 문자열의 개수는, (i+1) 번째 z의 위치가 [0..j]가 되었을 때, 바뀌기 위해 지나야 하는 문자열의 개수 합입니다.
// 지나야 하는 문자열의 개수를 알아둔다면, 문자열을 만들 필요 없이 값으로만 K에서 제하면서 찾아가면서, K번째 문자열을 특정할 수 있습니다.

// 그럼 문자열의 개수는 어떻게 구하느냐? 
// -> a N개 사이에 z M개를, 한 자리에 0개 이상 배치하는 것이기 때문에, 중복 조합을 사용해서 (N+M) C (M) 으로 지나야 하는 문자열의 개수를 쉽게 구할 수 있습니다. 

// ...
// 1 1 1 / aazzza
// 2 0 0 / azaazz -> 첫 번째 z의 위치가 2가 됨. (앞 az는 고정되고 뒤 aazz 가 변한다. 지나야 하는 문자열 개수는 a 2개 에 z 2개를 넣는 4C2 (6))
//                ->    두 번째 z의 위치가 0이 됨. (앞 azaaz는 고정된다. 지나야 하는 문자열 개수는 1개)
// 2 1 0 / azazaz ->    두 번째 z의 위치가 1이 됨. (앞 azaz는 고정되고 뒤 az가 변한다. 지나야 하는 문자열 개수는 a 1개 에 z 1개를 넣는 2C1 (2))
// 2 1 1 / azazza
// 2 2 0 / azzaaz ->    두 번째 z의 위치가 2가 됨. (앞 azz는 고정되고 뒤 aaz가 변한다. 지나야 하는 문자열 개수는 a 2개 에 z 1개를 넣는 3C1 (3))
// 2 2 1 / azzaza
// 2 2 2 / azzzaa
// 3 0 0 / zaaazz -> 첫 번째 z의 위치가 3이 됨. (앞 z는 고정되고 뒤 aaazz 가 변한다. 지나야 하는 문자열 개수는 a 3개 에 z 2개를 넣는 5C2 (10))
//                ->    두 번째 z의 위치가 0이 됨. (앞 zaaaz는 고정된다. 지나야 하는 문자열 개수는 1개)
// 3 1 0 / zaazaz ->    두 번째 z의 위치가 1이 됨. (앞 zaaz는 고정되고 뒤 az가 변한다. 지나야 하는 문자열 개수는 a 1개 에 z 1개를 넣는 2C1 (6)))
// ...

// 위에서 찾은 규칙성을 다시 살펴보겠습니다.
// 첫 번째 z의 위치가 바뀌기 위해선 1 (2C2), 3 (3C2), 6 (4C2), 10 (5C2) 개의 문자열을 지나야 합니다.
// 두 번째 z의 위치가 바뀌기 위해선 1 (1C1), 2 (2C1), 3 (3C1) 개의 문자열을 지나야 합니다. (단, 첫 번째 z의 위치를 넘지 않는다.)
// 파스칼 삼각형 값입니다. N과 M은 크지 않으니 arr 2차원 배열에 저장한 뒤 빠르게 액세스할 수 있도록 하였습니다.

// arr[i][j] = 
// 행 : [m - i] 번째 z의
// 열 : 위치가 [j]일 때
//      지나야 하는 문자열의 개수 총합
// -> {j=0 : 0}, {j>0 : (i+(j-1)) C (j-1)}. a (j-1)개 에 z i개를 넣는 경우의 수
//     1  1  1  1  1  1 ...
//  0  1  2  3  4  5  6 ...
//  0  1  3  6 10 15 21 ...
//  0  1  4 10 20 35 56 ...

// 첫 번째 z부터 위치를 특정하면서, 지나온 문자열의 개수를 빼면서 K번째 문자열을 특정하고, 범위 밖으로 나간다면 -1을 출력하면 됩니다.

// Ex. (N, M, K) = (3, 3, 9)
// 0번째 z의 위치 구하기
// -> arr[3] (arr[m - i]) 에서 K보다 작은 가장 큰 수 구하기 -> arr[3][2] (4) 
// -> 0번째 z의 위치는 2임. az( a2 z2 ) 
// -> K 에서 4 빼기 (이미 지나간 문자열 들이므로)
// 1번째 z의 위치 구하기
// -> arr[2] 에서 남은 K (5) 보다 작은 가장 큰 수 구하기 -> arr[2][2] (3)
// -> 1번째 z의 위치는 2임. azz ( a2 z1 )
// -> K 에서 3 빼기 (이미 지나간 문자열 들이므로)
// 2번째 z의 위치 구하기
// -> arr[1] 에서 남은 K (2) 보다 작은 가장 큰 수 구하기 -> arr[1][1] (1)
// -> 2번째 z의 위치는 1임. azzaza
// -> K 에서 1 빼기
// K 가 1이 남았다면, 남은 z의 위치가 다 0인 문자열이 정답 문자열

// 문제를 분석함으로써 정답을 구할 수 있음을 알았으니, 이를 순서대로 구현해보겠습니다.
// 1. [m - i번째 z의 위치가 j일 때 지나야 하는 문자열의 개수 총합]을 저장하는 arr 배열 생성하기
// 2. 0번째 z 부터 시작해서, K를 빼가면서 문자열 탐색하기
// 3. 생성 가능한 문자열 외라면 -1을 출력하고, 생성 가능하다면 selected 배열에 기록한 위치대로 z 삽입하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Dictionary {
    int n, m;
    long k;
    public Dictionary(int n, int m, long k) {
        this.n = n;
        this.m = m;
        this.k = k;
    }
    
    public void solve() {
        // 1. [m - i번째 z의 위치가 j일 때 지나야 하는 문자열의 개수 총합]를 저장하는 arr 배열 생성하기
        long[][] arr = new long[m+1][n+2];
        Arrays.fill(arr[0], 1L);

        for (int i = 1; i < m+1; i++) {
            arr[i][0] = 0L;
            for (int j = 1; j < n+2; j++) {
                // 1-1. aCb = (a-1)C(b-1) + (a-1)Cb 이다. (arr[i-1][j] + arr[i][j-1])
                // 1-2. K는 최대 10억이다. 오버플로우 방지를 위해 Integer.MAX_VALUE 최댓값을 걸어둔다.
                arr[i][j] = Math.min(Integer.MAX_VALUE, arr[i-1][j] + arr[i][j-1]);
            }
        }
        
        long now = k;
        int[] selected = new int[m]; // Z의 위치를 기록하는 배열
        boolean done = true; // 만들 수 있는 문자열 개수가 K보다 작은가?

        // 2. 0번째 z 부터 시작해서, K를 빼가면서 문자열 탐색하기
        for (int idx = 0; idx < m; idx++) {
            // 2-1. 남은 K (now)보다 작은 가장 큰 수 구하기
            int l = 0;
            while (l < n + 1) {
                if (arr[m - idx][l + 1] >= now) break;
                l += 1;
            }

            // 2-2. 만약 생성 가능한 문자열 외라면, n + 1 (끝) 까지 갔을 것.
            if (l == n + 1) {
                done = false;
                break;
            }

            // 2-3. 지나간 문자열 개수는 now 에서 빼고, idx번째 z의 위치는 l이라고 기록.
            now -= arr[m - idx][l]; 
            selected[idx] = l;
        }
        
        // 3. 생성 가능한 문자열 외라면 -1을 출력하고, 생성 가능하다면 selected 배열에 기록한 위치대로 z 삽입하기
        if (done) {
            int cntA = 0; // 출력한 A의 개수
            for (int s : selected) {
                // 3-1. 현재 Z의 위치 정보에 맞게 A를 먼저 출력하고, 그 다음 Z를 출력하기
                while (cntA != n - s) {
                    System.out.print("a"); cntA += 1;
                }
                System.out.print("z");
            }
            // 3-2. 남은 A 출력하기
            while (cntA != n) {
                System.out.print("a"); cntA += 1;
            }
        }
        else System.out.print(-1);
        
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        long k = Long.parseLong(st.nextToken());
    
        Dictionary d = new Dictionary(n, m, k);
        d.solve();
    }
}
