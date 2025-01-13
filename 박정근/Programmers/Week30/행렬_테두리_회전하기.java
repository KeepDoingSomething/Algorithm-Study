/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.07(Tue)
 * Runtime   : 41.72ms
 * Memory    : 104MB
 * Algorithm : Brute Force
 */

// >> 첫 번째 풀이 ( 완전 탐색 )
// 완전 탐색으로 풀이했습니다. (Solve Time : 0h 12m)
// 지문대로 구현했습니다. 개인적으로 이런 문제를 풀 때, 배열을 회전 탐색하는 본인만의 방법을 손에 녹여두는 게 좋다고 생각합니다. 그만큼 좋은 문제인 것 같습니다!

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        // 1. 행렬 채워넣기
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) matrix[i][j] = i*columns+j+1;
        }
        
        // 2. 쿼리 수행하기
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};
        for (int q = 0; q < queries.length; q++) {
            int start_i = queries[q][0]-1; // 시작점
            int start_j = queries[q][1]-1;
            int now_i = start_i; // 현재 위치
            int now_j = start_j;
            int dir = 0; // 방향
            
            int first_num = matrix[now_i][now_j]; // 회전을 시작하는 곳의 숫자는 저장했다가 나중에 넣기
            int min_num = first_num; // 회전 실행한 숫자 중 가장 작은 숫자
            matrix[now_i][now_j] = matrix[now_i + dy[dir]][now_j + dx[dir]]; // 행렬 값 한 칸씩 받아오기
            now_i += dy[dir]; now_j += dx[dir]; // 다음 칸 이동
            
            while(!(start_i == now_i && start_j == now_j)) {
                // 회전 범위 안이 아니라면, 탐색 방향 변경
                if (!((queries[q][0] - 1 <= now_i + dy[dir] && now_i + dy[dir] <= queries[q][2] - 1) && (queries[q][1] - 1 <= now_j + dx[dir] && now_j + dx[dir] <= queries[q][3] - 1)))
                    dir += 1;
                
                matrix[now_i][now_j] = matrix[now_i + dy[dir]][now_j + dx[dir]]; // 행렬 값 한 칸씩 받아오기
                min_num = Math.min(min_num, matrix[now_i][now_j]); // 가장 작은 숫자 업데이트
                now_i += dy[dir]; now_j += dx[dir]; // 다음 칸 이동
            }
            
            matrix[now_i - dy[dir]][now_j - dx[dir]] = first_num; // 시작하는 곳의 숫자 넣기
            answer[q] = min_num; // answer 배열에 추가
        }
        return answer;
    }
}
