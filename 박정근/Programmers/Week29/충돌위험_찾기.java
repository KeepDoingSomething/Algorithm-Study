/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.19(Thu)
 * Runtime   : 74.60ms
 * Memory    : 104MB
 * Algorithm : Brute Force
 */

// >> 첫 번째 풀이 ( 완전 탐색 )
// 완전 탐색으로 풀이했습니다. (Solve Time : 1h 8m)
// 지문대로 구현했습니다. 다른 사람들의 풀이를 보니 HashMap이나 Queue를 사용한 것 같은데, 로봇의 개수가 작아서 단순 반복문으로도 원활하게 돌아가네요.

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int n = routes.length;
        
        // visited[i][j][k] = (i, j)에 로봇이 언제 방문했는지 / 몇 대의 로봇이 방문했는지를 기록
        int[][][] visited = new int[101][101][2];
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                for (int k = 0; k < 2; k++) visited[i][j][k] = 0;
            }
        }
        int visited_cnt = 1;
        
        // robots[i][j] = i번째 로봇의 정보 (행 좌표, 열 좌표, 현재 목적지 포인트 번호)
        int[][] robots = new int[n][3];
        for (int idx = 0; idx < n; idx++) {
            // 처음은 routes[idx][0] 포인트를 따라간다.
            robots[idx][0] = points[routes[idx][0]-1][0];
            robots[idx][1] = points[routes[idx][0]-1][1];
            robots[idx][2] = 0; // 0번 포인트 따라가기 기록
            
            // 현재 visited_cnt에서 아직 해당 좌표를 방문하지 않았다면, 방문한 로봇 개수 초기화
            if (visited[robots[idx][0]][robots[idx][1]][0] != visited_cnt)
                visited[robots[idx][0]][robots[idx][1]][1] = 0;
            // 현재 visited_cnt에서 해당 좌표를 방문했는데, 1대의 로봇이 이미 방문한 상태라면 위험 상황 1 증가
            else if (visited[robots[idx][0]][robots[idx][1]][0] == visited_cnt && visited[robots[idx][0]][robots[idx][1]][1] == 1) 
                answer += 1;
            
            visited[robots[idx][0]][robots[idx][1]][0] = visited_cnt;
            visited[robots[idx][0]][robots[idx][1]][1] += 1;
        }
        
        // 로봇이 다 움직일 때 까지 무한 반복
        while (true) {
            // robot_can_go : 한 개의 로봇도 움직이지 않으면 (-1, -1) false 로 반복문 종료
            boolean robot_can_go = false;

            // 다음 visited_cnt (다음 스텝)
            visited_cnt += 1;
            
            for (int idx = 0; idx < n; idx++) {
                // 로봇이 (-1, -1) 이라면, (다 움직였으면) continue
                if (robots[idx][0] == -1 && robots[idx][1] == -1) continue;
                // 움직일 수 있으면 robot_can_go 는 true
                robot_can_go = true;
                
                // 로봇이 현재 목적지 포인트에 도착했는가?
                if (robots[idx][0] == points[routes[idx][robots[idx][2]]-1][0] && robots[idx][1] == points[routes[idx][robots[idx][2]]-1][1]) {
                    // 마지막 포인트인가?
                    if (routes[idx].length - 1 == robots[idx][2]) {
                        robots[idx][0] = -1;
                        robots[idx][1] = -1;
                        continue;
                    }
                    
                    // 다음 포인트
                    robots[idx][2] += 1;
                }
                

                // 행 이동 먼저, 이후 열 이동
                if (robots[idx][0] < points[routes[idx][robots[idx][2]]-1][0]) robots[idx][0] += 1;
                else if (robots[idx][0] > points[routes[idx][robots[idx][2]]-1][0]) robots[idx][0] -= 1;
                else if (robots[idx][1] < points[routes[idx][robots[idx][2]]-1][1]) robots[idx][1] += 1;
                else if (robots[idx][1] > points[routes[idx][robots[idx][2]]-1][1]) robots[idx][1] -= 1;
                
                
                // 현재 visited_cnt에서 아직 해당 좌표를 방문하지 않았다면, 방문한 로봇 개수 초기화
                if (visited[robots[idx][0]][robots[idx][1]][0] != visited_cnt)
                    visited[robots[idx][0]][robots[idx][1]][1] = 0;
                // 현재 visited_cnt에서 해당 좌표를 방문했는데, 1대의 로봇이 이미 방문한 상태라면 위험 상황 1 증가
                else if (visited[robots[idx][0]][robots[idx][1]][0] == visited_cnt && visited[robots[idx][0]][robots[idx][1]][1] == 1) 
                    answer += 1;

                visited[robots[idx][0]][robots[idx][1]][0] = visited_cnt;
                visited[robots[idx][0]][robots[idx][1]][1] += 1;
            }
            
            // robot_can_go 가 false 라면 반복문을 종료한다.
            if (!robot_can_go) break;
        }
        
        // 위험 상황 횟수 반환
        return answer;
    }
}
