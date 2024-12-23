/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.17(Tue)
 * Runtime   : 75.81ms
 * Memory    : 110MB
 * Algorithm : Greedy + Sort
 */

// >> 첫 번째 풀이 ( 그리디 + 정렬 )
// 그리디 정렬로 풀이했습니다. (Solve Time : 0h 5m)
// 귤 크기마다 개수를 센 뒤, 개수가 많은 귤 종류를 먼저 담으면 서로 다른 종류의 수가 최소가 됩니다.

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // ArrayList 에 특정 크기의 귤 개수를 저장했습니다. (크기는 저장하지 않아도 됩니다.)
        ArrayList<Integer> tangerine_cnt = new ArrayList<Integer>();
        
        // 세기 쉽게 정렬했습니다. 100,000번만 세면 됩니다.
        Arrays.sort(tangerine);
        int now_tangerine = tangerine[0];
        int now_cnt = 1;
        for (int i = 1; i < tangerine.length; i++) {
            if (now_tangerine == tangerine[i]) now_cnt += 1;
            else {
                tangerine_cnt.add(now_cnt);
                now_tangerine = tangerine[i];
                now_cnt = 1;
            }
        }
        tangerine_cnt.add(now_cnt);
        
        Collections.sort(tangerine_cnt);
        // 개수가 많은 귤 종류부터 넣습니다.
        for (int i = tangerine_cnt.size() - 1; i >= 0; i--) {
            answer += 1;
            k -= tangerine_cnt.get(i);
            if (k <= 0) break;
        }
        
        return answer;
    }
}
