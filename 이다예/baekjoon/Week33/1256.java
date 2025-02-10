import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {
    /* dp 배열의 자료형에 double 을 사용한 이유
    *  조합의 개수는 엄청나게 빠르게 증가함.
    *  조합의 공식으로 a, z의 개수를 넣어서 계산해보면 조합 값은 10^29 이상이 될 수 있음 == 100경 이상
    *  int 는 21억까지, long 은 9경 까지, double 은 최대 10^308 까지 저장 가능
    *  double 은 실수형이라 정확한 숫자가 아니지만, 10억 보다 크냐? 만 알면 되므로 상관없음
    *  (K값의 범위가 10억까지임)
    *  BigInteger 도 이론적으로 무한한 정수를 다룰 수 있지만, 객체이므로 연산 속도가 느림, 정확한 값 필요X */
    static double[][] dp= new double[101][101]; // a를 n개로, z를 m개로 만들 수 있는 문자열의 개수
    static StringBuilder sb = new StringBuilder();
    /* StringBuilder 을 전역으로 선언한 이유는, 하나의 객체를 재사용하기 위함
    *  또한 문자열을 자주 수정해야해서 String 대신 StringBuilder 을 사용한 것임
    *  만약, String 을 사용한다면, 문자열이 수정될 때마다 매번 새로 생성되어 성능이 저하됨
    *  이때, StringBuilder 을 사용한다면, 기존 객체에 수정(추가)되므로 메모리가 절약됨 */

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            // br.readLine()은 한 번만 사용하면 한 줄 전체를 읽음
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if(check(n, m)<k) { // 못구하는 경우
                System.out.println("-1");
            }else{
                makeString(n, m, k); // 문자열 만들기
                System.out.println(sb.toString());
            }
    }

    public static double check(int a, int z){
        if(a==0||z==0) return 1;
        if(dp[a][z]!=0) return dp[a][z];

        return dp[a][z] = Double.min(check(a-1, z)+check(a,z-1), 1000000001);
        /*
        * 사전순으로 a, z순
        * a 3개, z 3개 일때, 만들 수 있는 문자열은?
        * a로 시작 + a 2개+ z 3개로 만들수 있는 문자열
        * z로 시작 + a 3개+ z 2개로 만들 수 있는 문자열
        * ==> 점화식 도출
        * */
    }

    public static void makeString(int a, int z, double k){
        if(a==0){ // a 개수가 0인 경우 z로만 채우기
            for (int i = 0; i<z; i++) {
                sb.append("z");
            }
            return;
        }
        if(z==0) {// z 개수가 0인 경우 a로만 채우기
            for (int i = 0; i<a; i++){
                sb.append("a");
            }
            return;
        }
        // a가 0이 아니니까 맨앞에 a가 있다고 치고 시작함.
        double check = check(a-1, z); // 맨 앞에 a로 시작하는 경우의수
        if(k>check) {
            sb.append("z");
            makeString(a, z-1, k-check);
            // 마지막 매개변수는 맨앞에 a가 있는 경우를 k에서 빼주는거임 == check 이후의 값을 찾을거고 k도 그만큼 줄이겠다.
        }else{ // 맨앞에 a로 시작하는 문자열 경우의 수가 찾고자 하는 문자열 순서보다 작으면 맨앞에 a가 맞다.
            sb.append("a");
            makeString(a-1, z, k);
        }
    }
}