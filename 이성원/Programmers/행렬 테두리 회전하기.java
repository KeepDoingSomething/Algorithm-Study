import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] map = new int[rows][columns];
        initMap(map); // map 초기화

        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;

            int result = circulateMin(x1, y1, x2, y2, map);
            answer[i] = result;
        }

        return answer;
    }

    public void initMap(int[][] map) {
        int value = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = value;
                value++;
            }
        }
    }

    public int circulateMin(int x1, int y1, int x2, int y2, int[][] map) {
        int firstNum = map[x1][y2]; // 첫 번째 순회 시 덮어써질 숫자
        int min = firstNum;

        for(int i = y2-1; i >= y1; i--){
            min = Math.min(min, map[x1][i]);
            map[x1][i+1] = map[x1][i];
        }


        // 맨 왼쪽 줄 순회
        for(int i=x1+1; i<=x2; i++){
            min = Math.min(min,  map[i][y1]);
            map[i-1][y1] =  map[i][y1];
        }

        // 숫자를 좌로 이동 (하단)
        for(int i=y1+1; i<=y2; i++){
            min = Math.min(min,  map[x2][i]);
            map[x2][i-1] = map[x2][i];
        }

        for(int i=x2-1; i>=x1; i--){
            min = Math.min(min, map[i][y2]);
            map[i+1][y2] = map[i][y2];
        }

        // 저장해 둔 첫 번째 값 복구
        map[x1 + 1][y2] = firstNum;

        return min;
    }
}
