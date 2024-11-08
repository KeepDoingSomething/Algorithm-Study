/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.08(Fri)
 * Runtime   : 120 ms
 * Memory    : 14688 KB
 * Algorithm : Dynamic Programming
 */

/**

// >> 첫 번째 풀이 ( 3차원 DP )
// 처음 떠올린 풀이입니다. 첫 번째 집의 색과 N 번째 집의 색을 다르게 하기 위한 방법을 고민해보다가, 떠올린 아이디어로 최적화 없이 구현했습니다.
// 첫 번째 집을 색 j로 칠했을 때, i번째 집을 칠할 때 색 k로 칠할 수 있는 경우의 수를 구하도록 하여, "첫 번째 집을 어떻게 칠했는지에 대한 상태"를 계속 가져가려고 했습니다.
// 처음엔 Scanner 를 사용했는데, BufferedReader 와 StringTokenizer 를 사용하니 성능이 확 좋아지더군요. ( 260ms -> 120ms / 20364 KB -> 14688 KB )

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main {
    static public void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j][k] : 첫 번째 집을 색 j로 칠했을 때, i번째 집을 칠할 시 색 k로 칠할 수 있는 경우의 수
        int[][][] dp = new int[n+1][3][3];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) dp[i][j][k] = -1;
            }
        }

        for (int i = 0; i < n+1; i++) {
            if (i == 0) {
                // 0번째 집은 j와 k가 같도록 설정한다.
                for (int j = 0; j < 3; j++) dp[i][j][j] = arr[i][j];
            }
            else {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        // i-1 번째 집의 색과 다르게 칠하면 된다. 
                        // i번째 집에 j 색을 칠하려고 한다면, i-1번째 집은 j-1 색이나 j+1 색을 사용한다.
                        
                        int left = dp[i-1][j][(k > 0) ? (k - 1) : 2]; // i-1번째 집을 k-1 색으로 칠했을 때의 경우의 수
                        int right = dp[i-1][j][(k + 1) % 3]; // i-1번째 집을 k+1 색으로 칠했을 때의 경우의 수
                        if (left != -1 && right != -1) dp[i][j][k] = Math.min(left, right);
                        else { 
                            if (left != -1) dp[i][j][k] = left;
                            else dp[i][j][k] = right;
                        }
                        if (i < n && dp[i][j][k] != -1) dp[i][j][k] += arr[i][k];
                    }
                }
            }
        }

        int result = -1;
        for (int i = 0; i < 3; i++) {
            if (dp[n][i][i] != -1) {
                if (result == -1 || (result != -1 && result > dp[n][i][i])) result = dp[n][i][i];
            }
        }
        System.out.println(result);
    }
}

**/

// >> 두 번째 풀이 ( 2차원 DP )
// 첫 풀이를 최적화하기 위해 다시 곱씹으며 풀이해보니 고칠 점이 많았습니다. 
// 1. 첫 번째 집을 어떻게 칠했는지에 대한 상태를 굳이 가져가면서 N+1 행까지 가는 것 보다는, 특정 색부터 시작해서 1번부터 N번째 집까지 칠한 경우의 수를 구한 뒤 1번과 N번 색이 다른 경우만 처리해주면 되었습니다.
// 2. 굳이 DP 배열을 -1로 설정하는 것 보다는, 초기값을 최댓값으로 설정한 뒤 경우의 수마다 Math.min 연산을 해주었습니다. 단순히 Integer.MAX_VALUE 를 사용하면 오버플로우가 발생할 수 있기에 적당히 큰 수를 빼주어 초기값으로 설정했습니다.
// 첫 번째 풀이와 메모리 / 시간 차이는 크게 나지 않았습니다만, (두 번째 풀이 성능이 살짝 더 좋습니다.) 가독성 면에서 더 나아진 것 같습니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
    static public int MAXINT = Integer.MAX_VALUE - 10000000;
	static public void main(String []args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        
        // arr : 집을 칠하는 비용 배열
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        
        int res = MAXINT;
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], MAXINT);
        
        for (int color = 0; color < 3; color++) {
            // 특정 색부터 시작해서 N번 집까지 칠했을 때, 1번 집과 색이 동일하지 않은 경우를 찾는다.
            Arrays.fill(dp[0], MAXINT);
            dp[0][color] = arr[0][color]; // 다른 색깔을 다 사용해서 탐색하면 N번째 집을 처음에 어떤 색으로 칠했는지 모른다. 한 색깔로만 먼저 칠해본다.

            // 최소 비용 탐색
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    // i-1 번째 집의 색과 다르게 칠하면 된다. 
                    // i번째 집에 j 색을 칠하려고 한다면, i-1번째 집은 j-1 색이나 j+1 색을 사용한다.
                    dp[i][j] = arr[i][j] + Math.min(dp[i-1][((j == 0) ? 2 : (j - 1))], dp[i-1][(j+1)%3]);
                }
            }

            for (int j = 0; j < 3; j++) {
                if (color == j) continue; // 첫 번째 집의 색과 같이 칠했을 경우는 제외
                res = Math.min(res, dp[n-1][j]);
            }
        }
        
        System.out.println(res);
    }
}
