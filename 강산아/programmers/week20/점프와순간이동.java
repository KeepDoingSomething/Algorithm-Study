/**
 * Author    : Kang San Ah
 * Date      : 2024.10.12(Sat)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 구현
 */

public class 점프와순간이동 {
    public int solution(int n) {

        int answer = 0; // 건전지 사용량

        while (n != 0) {
            if (n % 2 == 0){
                n /= 2;
            } else {
                n--;
                answer++;
            }
        }
        return answer;
    }
}
