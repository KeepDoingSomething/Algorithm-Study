package back_tracking;

/**
 * Author    : 김현정
 * Date      : 2024.09.05(목)
 * Runtime   :
 * Memory    :
 * Algorithm : 백트래킹 - LV2_68936 쿼드 압축 후 세기
 */
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] answer = solution.solution(new int[][]{{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}});
        for(int a : answer){
            System.out.println(a);
        }
    }

    static int[] answer;
    public int[] solution(int[][] arr) {
        //전체 압축이 가능한지 볼 것
        //안되면 반으로 줄일 것
        answer = new int[2];
        dfs(arr, 0, 0, arr.length);

        return answer;
    }

    public void dfs(int[][] arr, int x, int y, int size) {
        boolean flag = true;

        for(int i = x; i < x+ size; i++) {
            for(int j = y; j < y + size; j++) {
                if(arr[i][j] != arr[x][y]) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
        }

        int newSize = size/2;
        if(flag) {
            if(arr[x][y] == 1){
                answer[1]++;
            } else {
                answer[0]++;
            }
            return;
        }
        dfs(arr, x, y, newSize);
        dfs(arr, x, y + newSize, newSize);
        dfs(arr, x +  newSize, y, newSize);
        dfs(arr, x + newSize, y + newSize, newSize);
    }
}
