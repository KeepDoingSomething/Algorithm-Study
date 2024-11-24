/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.14(Thu)
 * Runtime   : 3.63ms / 1.67ms
 * Memory    : 80.6MB / 84.2MB
 * Algorithm : Backtracking / Bitmask + Brute Force
 */


// >> 첫 번째 풀이 ( 백트래킹 )
// 그리디, DP 를 사용할 수 있는지 고민해보다가, 시간 복잡도가 가능해서 백트래킹으로 풀이했습니다.
// 특정 점수를 가져가는지 / 가져가지 않는지에 대한 백트래킹으로, 최대 O(2^11) 만큼 재귀함수를 호출했습니다.
// 거의 완전 탐색에 가까워서, 구현량도 많고 시간 소모도 컸습니다. (ArcheryComp 클래스를 만들어 구현했습니다.)

/*
TC 1.
n = 5
어피치 화살  [2,1,1,1,0,0,0,0,0,0,0]
필요 화살    [3,2,2,2,1,1,1,1,1,1,1]

TC 2.
n = 9
어피치 화살  [0,0,1,2,0,1,1,1,1,1,1]
필요 화살    [1,1,2,3,1,2,2,2,2,2,2]

- 그리디
높은 원만 먼저 가져간다고 해도 이길 수 없음. (TC 1)

- DP
화살로 하는 냅색?
최적의 경우를 가져간다고 해도, 어피치 점수를 가져가는 경우도 고려해야 함. 

n의 최대 길이가 10이고, info 의 길이가 11인 이유가 있을 것이다.

- 백트래킹
O(2^11) 로, 한 원의 점수를 가져갔다 / 안 가져갔다 상태를 본다.
*/

/*
import java.util.Arrays;

class ArcheryComp {
    private int all_arrow; // n
    private int[] enemy_arr; // 어피치가 발사한 화살
    
    private int[] now_status; // -1 : 안 가져간다 (<=k) 1 : 가져간다 (>k)
    private int[] now_diff_arr; // 현재 라이언이 발사한 화살
    private int max_diff; // 최대 (라이언 점수 - 어피치 점수)
    private int[] max_diff_arr; // 최대 점수 차가 나는 라이언이 발사한 화살
    
    public ArcheryComp(int n, int[] info) {
        this.all_arrow = n;
        this.enemy_arr = new int[11];
        for (int i = 0; i < 11; i++) this.enemy_arr[i] = info[i];
        
        this.now_status = new int[11];
        Arrays.fill(this.now_status, -1);
        this.now_diff_arr = new int[11];
        Arrays.fill(this.now_diff_arr, 0);
        this.max_diff = 0;
        this.max_diff_arr = new int[11];
        Arrays.fill(this.max_diff_arr, 0);
        solve(0, 0);
    }

    // 가장 낮은 점수를 많이 맞힌 배열을 반환
    public int[] compare(int[] a, int[] b) {
        for (int i = 10; i >= 0; i--) {
            if (a[i] > b[i]) return a;
            else if (a[i] < b[i]) return b;
        }
        return a;
    }

    // 디버그용
    public void _PRINTARR(int[] a) {
        System.out.println("DEBUG : PRINT ARRAY >>");
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + " ");
        System.out.println();
    }

    public void solve(int cnt, int now_arrow) {
        // >> 종료 조건
        if (cnt == 11) {
            // 1. now_diff 구하기
            int now_score = 0;
            int ene_score = 0;
            for (int i = 0; i < 11; i++) {
                if (now_status[i] == -1 && enemy_arr[i] > 0) ene_score += 10 - i; // 어피치도 화살을 못 맞혔으면 가져가지 않는다.
                else if (now_status[i] == 1) now_score += 10 - i;
            }
            
            if (now_score <= ene_score || now_score - ene_score < max_diff) return;
            // 2. now_diff >= max_diff 라면 최적 max_diff_arr 구하기

            // 남은 화살들 할당하기 (현재 경우에서 만들 수 있는, 낮은 점수에 많이 화살이 맞은 화살 개수 배열 만들기)
            int last = all_arrow - now_arrow;
            int idx = 10;
            while (last > 0) {
                if (now_status[idx] == -1) { // 점수 가져가지 않은 원판 : 어피치가 쏜 화살 수 넘지 않게끔 화살 할당
                    int go = Math.min(last, enemy_arr[idx] - now_diff_arr[idx]);
                    now_diff_arr[idx] += go;
                    last -= go;
                }
                else { // 점수 가져간 원판 : 남은 화살 다 할당
                    now_diff_arr[idx] += last;
                    last = 0;
                }
                
                idx -= 1;
            }

            // DEBUG
            // System.out.println(now_score + " " + ene_score);
            // _PRINTARR(now_diff_arr);
            
            if (max_diff < now_score - ene_score) {
                for (int i = 0; i < 11; i++) max_diff_arr[i] = now_diff_arr[i];
            }
            else {
                // max_diff와 점수 차가 같을 경우 배열 비교
                int[] res_arr = compare(max_diff_arr, now_diff_arr);
                for (int i = 0; i < 11; i++) max_diff_arr[i] = res_arr[i];
            }

            max_diff = now_score - ene_score;
            return;
        }
        
        // >> 진행
        // 1. 안 가져간다 (적이 1발 이상 맞아야 안 가져갈 수 있음)
        now_status[cnt] = -1;
        now_diff_arr[cnt] = 0;
        solve(cnt + 1, now_arrow);

        // 2. 가져간다. (적이 쏜 화살 개수 + 1만큼 쏠 수 있을 정도로 화살이 남는가?)
        if (now_arrow + enemy_arr[cnt] + 1 <= all_arrow) {
            now_status[cnt] = 1;
            now_diff_arr[cnt] = enemy_arr[cnt] + 1;
            solve(cnt + 1, now_arrow + enemy_arr[cnt] + 1);
        }
    }
    
    public int[] getAnswer() {
        if (max_diff == 0) return new int[]{-1};
        return max_diff_arr; 
    }
}

class Solution {
    public int[] solution(int n, int[] info) {
        ArcheryComp ac = new ArcheryComp(n, info);
        int[] answer = ac.getAnswer();
        
        return answer;
    }
}
*/

// >> 두 번째 풀이 ( 비트마스킹 + 완전 탐색 )
// 완전 탐색에 가깝다는 특성을 이용해, 2^11 을 비트마스크로 탐색하자는 아이디어를 얻었습니다.
// 어피치가 점수를 가져간 상태를 0, 라이언이 점수를 가져간 상태를 1로 두고 비트마스크를 이용해 완전 탐색을 돌렸습니다.
// 시간이 3.63ms -> 1.67ms로 절반 가량 줄었습니다. 
// 할 수 있다면 이후에 DP로도 풀이할 수 있을 것 같은데, 점화식이 잘 떠오르진 않네요.

import java.util.Arrays;

class ArcheryComp {
    private int all_arrow;
    private int[] enemy_arr;
    
    private int max_diff;
    private int[] max_arrow_arr;
    
    public ArcheryComp(int n, int[] info) {
        this.all_arrow = n;
        this.enemy_arr = new int[11];
        for (int i = 0; i < 11; i++) this.enemy_arr[i] = info[i];
        
        this.max_diff = 0;
        this.max_arrow_arr = new int[11];
        Arrays.fill(max_arrow_arr, 0);
        
        solve();
    }
    
    // 가장 낮은 점수를 많이 맞힌 배열을 반환
    public int[] compare(int[] a, int[] b) {
        for (int i = 10; i >= 0; i--) {
            if (a[i] > b[i]) return a;
            else if (a[i] < b[i]) return b;
        }
        return a;
    }
    
    public void solve() {
        for (int now = 1; now < 2048; now++) { // 00000000001 부터 11111111111 까지 탐색하기
            int lion_score = 0;
            int apeach_score = 0;
            int need_arrow = 0;
            for (int i = 0; i < 11; i += 1) {
                if ((int)(now & (1 << i)) != 0) {
                    need_arrow += enemy_arr[i] + 1;
                    lion_score += 10 - i;
                }
                else if ((int)(now & (1 << i)) == 0 && enemy_arr[i] > 0) {  // 어피치도 화살을 못 맞혔으면 가져가지 않는다.
                    apeach_score += 10 - i;
                }
            }
            
            if (!((need_arrow <= all_arrow) && (lion_score > apeach_score) && (lion_score - apeach_score >= max_diff))) continue;
            
            int[] now_arrow_arr = new int[11];
            int last = all_arrow - need_arrow; // 남은 화살은 라이언이 가져간 가장 낮은 점수에 모두 쓰거나, 어피치가 가져간 점수에 넣을 수 있을 만큼 넣는다.
            for (int i = 10; i >= 0; i--) {
                if ((int)(now & (1 << i)) != 0) { // 라이언이 점수를 가져간다.
                    now_arrow_arr[i] = enemy_arr[i] + 1 + last;
                    last = 0;
                }
                else { // 어피치가 점수를 가져간다.
                    int go = Math.min(last, enemy_arr[i]);
                    now_arrow_arr[i] = go;
                    last -= go;
                }
            }
            
            if (lion_score - apeach_score > max_diff) {
                for (int i = 0; i < 11; i++) max_arrow_arr[i] = now_arrow_arr[i];
            }
            else if (lion_score - apeach_score == max_diff) {
                int[] res_arr = compare(max_arrow_arr, now_arrow_arr);
                for (int i = 0; i < 11; i++) max_arrow_arr[i] = res_arr[i];
            }
            
            max_diff = lion_score - apeach_score;
        }
    }
    
    public int[] getAnswer() {
        if (max_diff == 0) return new int[]{-1};
        return max_arrow_arr;
    }
}

class Solution {
    public int[] solution(int n, int[] info) {
        ArcheryComp ac = new ArcheryComp(n, info);
        int[] answer = ac.getAnswer();
        
        return answer;
    }
}
