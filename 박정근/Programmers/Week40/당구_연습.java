/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.30(Sun)
 * Runtime   : 1.16ms
 * Memory    : 99.8MB
 * Algorithm : Math
 */

// >> 첫 번째 풀이 ( Math )
// 수학로 풀이했습니다. (Solve Time : ?h ??m)

// `머쓱이가 친 공이 벽에 부딪힐 때 진행 방향은 항상 입사각과 반사각이 동일하며,` 라는 조건을 못 읽어서,
// 입사각과 반사각이 동일하지 않을 때의 이동 거리 최솟값을 구하려고 했다가 나중에 발견했습니다................. 아주 머쓱하네요.

// 입사각과 반사각이 동일하다면, 두 공이 이동하는 점 선으로 만들어지는 삼각형이 합동임을 알 수 있습니다.
// 두 삼각형의 빗변 길이(점 선 길이) 합을 구하기 위해, 가로 길이와 세로 길이를 비례 관계로 구해 피타고라스 정리를 사용합니다.
// 원쿠션이 상하좌우 벽에서 일어났을 때 공의 이동 거리를 각각 구하여, 최솟값을 찾습니다.

// 목표 공에 맞으면 바로 멈추기 때문에, 목표 공에 맞는지에 대한 예외 처리를 진행해야 합니다.
// 저는 가로 길이와 세로 길이를 구한 dy, dx 배열에 목표 공에 맞는다면 계산에 포함하지 않도록 둘 다 0으로 만든 뒤 스킵했습니다.

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        int idx = 0;
        for (int[] ball : balls) {
            // 원쿠션이 Left, Up, Right, Down 에서 일어났을 때의 y, x 길이
            int[] dy = {Math.abs(startY - ball[1]),     
                        (Math.abs(startX - ball[0]) == 0 && startY < ball[1]) ? 0 : ((n - startY) + (n - ball[1])),
                        Math.abs(startY - ball[1]),
                        (Math.abs(startX - ball[0]) == 0 && startY > ball[1]) ? 0 : (startY + ball[1])};
            
            int[] dx = {(Math.abs(startY - ball[1]) == 0 && startX > ball[0]) ? 0 : (startX + ball[0]),
                        Math.abs(startX - ball[0]),
                        (Math.abs(startY - ball[1]) == 0 && startX < ball[0]) ? 0 : ((m - startX) + (m - ball[0])),
                        Math.abs(startX - ball[0])};
            
            
            int res = Integer.MAX_VALUE;
            for (int d = 0; d < 4; d++) {
                if (dy[d] == 0 && dx[d] == 0) continue; // 목표 공에 맞는 경우
                
                res = Math.min(res, (int)Math.pow(dy[d], 2) + (int)Math.pow(dx[d], 2));
            }
            
            answer[idx++] = res;
        }
        
        return answer;
    }
}
