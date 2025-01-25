import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int map[][];
    static int zero = 0;
    static int minusone = 0;
    static int one  = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i = 0 ; i < N ; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calculate(0, 0, N);

        System.out.println(minusone);
        System.out.println(zero);
        System.out.println(one);


    }

    public static void calculate(int row, int column, int size) {

        if(check(row, column, size)) {
            if(map[row][column] == 0){
                zero++;
            }
            else if(map[row][column] == -1) {
                minusone ++;
            }
            else {
                one++;
            }

            return;
        }

        int newSize = size / 3;

        calculate(row, column, newSize); // 왼쪽위
        calculate(row, column + newSize, newSize); // 위중앙
        calculate(row, column + 2*newSize, newSize); // 오른쪽 위

        calculate(row + newSize, column, newSize); // 왼쪽 가운데
        calculate(row + newSize, column + newSize, newSize); //중앙 가운데
        calculate(row + newSize, column + 2*newSize, newSize); // 오른쪽 가운데

        calculate(row + 2 * newSize, column, newSize); //왼쪽 아래
        calculate(row + 2 * newSize, column + newSize, newSize); //중앙 아래
        calculate(row + 2 * newSize, column + 2*newSize, newSize); // 오른쪽 아래




    }

    public static boolean check(int row, int column, int size) {

        int aw = map[row][column];

        for(int i = row; i < row + size; i++) {
            for(int j = column; j < column + size; j++ ) {
                if(aw != map[i][j]) {
                    return false;
                }
            }
        }


        return true;
    }


}