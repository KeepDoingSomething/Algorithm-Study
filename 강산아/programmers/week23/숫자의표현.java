/**
 * Author    : Kang San Ah
 * Date      : 2024.11.08(Fri)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 구현
 */

public class 숫자의표현 {

    public int solution(int n) {

        int answer = 0;

        for(int i=1; i<=n; i++) {
            int sum = 0;
            for(int j=i; j<=n; j++) {
                sum += j;

                if(sum==n) {
                    answer++;
                    break;
                } else if(sum>n) {
                    break;
                }
            }
        }

        return answer;
    }

}
