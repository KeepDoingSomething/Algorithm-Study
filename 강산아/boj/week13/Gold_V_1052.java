/**
 * Author    : Kang San Ah
 * Date      : 2024.08.15(Thu)
 * Runtime   : 1 sec
 * Memory    : 512 MB
 * Algorithm : Greedy Algorithm
 */

public class Main {


    static  int m;

    public int solution(int N, int K) {

        if (N < K) return 0;

        for (int i = 0; i < K - 1; i++) {
            m = 0;
            while (Math.pow(2, m) < N) {
                m++;
            }
            N -= Math.pow(2, m - 1);
            if (N == 0) return 0;
        }
        m = 0 ;
        while (Math.pow(2, m) < N){
            m ++ ;
        }
        return (int) (Math.pow(2, m) - N);
    }


    public static void main(String[] args) throws IOException {

        Main main = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(main.solution(N,K));

    }
}
