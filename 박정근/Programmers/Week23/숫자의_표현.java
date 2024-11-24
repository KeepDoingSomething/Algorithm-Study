/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.08(Fri)
 * Runtime   : 0.11ms
 * Memory    : 70MB
 * Algorithm :  Number Theory
 */

/**
// >> 첫 번째 풀이 ( 누적 합, 이분 탐색 )
// 특정 수를 연속한 자연수들의 합으로 표현하기 위해선, 1부터 i까지의 연속된 자연수의 합에서 1부터 j까지의 연속된 자연수의 합을 제하면 된다는 아이디어를 떠올려 시작했습니다. (n = S[1..i] - S[1..j])
// 1부터 n까지 순차적으로 탐색하면서, (1부터 i까지의 합 - n)을 만족하는 1부터 j까지의 합이 있는지 이분 탐색을 통해 찾도록 구상한 뒤 풀이했습니다.
// 이분 탐색을 수월하게 진행하기 위해, 1부터 n까지 탐색하는 과정에서 누적 합을 이용해 미리 연속된 자연수들의 합을 기록해두었습니다. n의 제한이 최대 10000이기에 O(nlogn) 풀이가 가능하다고 생각했습니다.

import java.util.Arrays;

class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] sums = new int[n+1];
        Arrays.fill(sums, 0);
        for (int i = 1; i <= n; i++) {
            // 누적 합 기록
            sums[i] = i + sums[i-1];

            int k = sums[i] - n; // S[1..i] - n 을 만족하는 1부터 j까지의 합
            if (k < 0) continue; // 음수라면 제외
            
            int start = 0;
            int end = i; // 0부터 i까지의 누적 합 배열 안에서 찾아보기
            
            while (start <= end) {
                int mid = (start + end) / 2;
                if (sums[mid] == k) {
                    answer += 1;
                    break;
                }
                else if (sums[mid] < k) start = mid + 1;
                else end = mid - 1;
            }
        }
        
        return answer;
    }
}

**/

// >> 두 번째 풀이 ( 수학 )
// 분명 최적화할 수가 있을 것 같아서 다시 곱씹어봤습니다. 느낌 상 이분 탐색이 정해는 아닌 것 같았습니다.
// S[1..i] - n = S[1..j] 조건이 충족한다면, S[1..j] 를 2로 곱한 값은 연속된 두 자연수의 곱입니다. S[1..j] = (j * (j + 1)) / 2 이기 때문입니다.
// 따라서, k 값에 2를 곱한 뒤 제곱근을 구하고, 구한 제곱근을 l이라 할 때 l * (l + 1) 이 k와 같다면 조건을 충족할 수 있습니다.

import java.util.Arrays;
class Solution {
    public int solution(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int k = ((i * (i + 1) / 2) - n) * 2;
            if (k < 0) continue;
            int l = (int)Math.sqrt(k);
            if (l * (l + 1) == k) answer += 1;
        }
        
        return answer;
    }
}
