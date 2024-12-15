import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int arr[];
    static int count = 0;
    static int N;
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        backTraking(0);


        System.out.println(count);

    }

    static void backTraking(int cnt) {

        if(cnt == N) {
            count++;
            return;
        }

        for(int i = 0; i <N; i++) {
            arr[cnt] = i; // 체스 위치 미리 두기
            if(possible(cnt)) {
                backTraking(cnt+1);
            }


        }
    }

    static boolean possible(int col) {
        for(int i =0; i < col; i++) {

            if(arr[col] == arr[i]) return false; // 같은 행에 있나 체크

            else if(Math.abs(arr[col] - arr[i]) == Math.abs(col- i)) return false; //대각선 체크
        }
        return true;
    }
}