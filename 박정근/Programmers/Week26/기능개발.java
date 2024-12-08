/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.25(Mon)
 * Runtime   : 0.04ms
 * Memory    : 78.5MB
 * Algorithm : Math
 */

// >> 첫 번째 풀이 ( 수학 )
// (100 - 현재 작업 진도) / (작업 속도) + (나누어 떨어지지 않는다면 1) 식을 통해 작업마다 배포 날짜를 구했습니다.
// 배포는 첫 번째 작업부터 순차적으로 이루어져야 하므로, 이전 작업이 늦게 끝난다면 다음 작업은 동시에 배포할 수 있고, 이전 작업이 빨리 끝난다면 다음 작업은 다음 배포 때 배포합니다.

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        int[] done_day = new int[n];

        // 배포 날짜 : (100 - 현재 작업 진도) / (작업 속도) + (나누어 떨어지지 않는다면 1)
        for (int i = 0; i < n; i++) {
            done_day[i] = ((100 - progresses[i]) / speeds[i]) + (((100 - progresses[i]) % speeds[i]) == 0 ? 0 : 1);
        }
        
        int[] res_arr = new int[n]; // 정답 배열은 길이가 가변적이므로, 스택 원리로 만들어냅니다.
        int res_arr_idx = 0;
        
        int now_task = 1; // 현재 동시에 배포할 수 있는 작업의 수
        int pre_task = done_day[0]; // 가장 늦게 끝난 이전 작업
        for (int i = 1; i < n; i++) {
            if (pre_task < done_day[i]) { // 이전 작업보다 늦게 끝난다면..
                res_arr[res_arr_idx++] = now_task; // 이전 작업들을 배포한다.
                
                pre_task = done_day[i]; // 새로운 배포 : i번째 작업
                now_task = 1; // 동시 배포 작업 수를 1로 초기화
            }
            else { // 이전 작업보다 빨리 끝난다면..
                now_task += 1; // 동시에 배포할 수 있다.
            }
        }
        res_arr[res_arr_idx++] = now_task; // 남은 작업 배포
        
        int[] answer = new int[res_arr_idx]; // 정답 배열
        for (int i = 0; i < res_arr_idx; i++) answer[i] = res_arr[i];
        
        return answer;
    }
}
