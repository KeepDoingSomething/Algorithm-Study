import java.util.Arrays;

public class Lv2_77485 {
    public int[] solution(int rows, int columns, int[][] queries) {
        int queryLen = queries.length;

        int[] answer = new int[queryLen];

        int[][] arr = new int[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                arr[i][j] = (i * columns) + (j + 1);
            }
        }

        for(int i = 0; i < queryLen; i++){
            int x1 = queries[i][0] - 1; int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1; int y2 = queries[i][3] - 1;

            int min = arr[x1][y1];
            int firstNum = arr[x1][y1]; // 시작점 저장

            // 좌
            for (int j = x1; j < x2; j++) {
                arr[j][y1] = arr[j + 1][y1];

                min = Math.min(min, arr[j][y1]);
            }

            // 하
            for (int j = y1; j < y2; j++) {
                arr[x2][j] = arr[x2][j + 1];

                min = Math.min(min, arr[x2][j]);
            }

            // 우
            for (int j = x2; j > x1; j--) {
                arr[j][y2] = arr[j - 1][y2];

                min = Math.min(min, arr[j][y2]);
            }

            // 상
            for (int j = y2; j > y1; j--) {
                arr[x1][j] = arr[x1][j - 1];

                min = Math.min(min, arr[x1][j]);
            }

            arr[x1][y1 + 1] = firstNum;

            answer[i] = min;
        }

        return answer;
    }

    public static void main(String[] args) {
        Lv2_77485 sol = new Lv2_77485();

        int[][] arr = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};

        sol.solution(6, 6, arr);
    }
}
