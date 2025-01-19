/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.19(Sun)
 * Runtime   : 0.09ms
 * Memory    : 89.7MB
 * Algorithm : Euclidean
 */

// >> 첫 번째 풀이 ( 유클리드 호제법 )
// 수학 + 유클리드 호제법으로 풀이했습니다. (Solve Time : 0h 36m)
// (w, h) 순서쌍마다 사용할 수 없는 정사각형의 개수를 하나씩 구해보면서 규칙을 찾아봤습니다.
// 그 결과, w, h가 서로소일 때 사용할 수 없는 정사각형의 개수는 w + h - 1 임을 알 수 있었습니다.
// 유클리드 호제법으로 최대 공약수를 구한 뒤, 찾은 규칙에 녹여내서 답을 출력합니다.

// 몇 개 테스트 케이스에서 틀리길래 왜인가 싶었는데, long 이 아니여서 오버플로우가 났던 것이였습니다.. ;ㅁ; long 으로 떡칠해서 해결했습니다.

class Solution {
    public long gcd(long a, long b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
    
    public long solution(long w, long h) {
        long g = gcd(w, h);
        long answer = (w * h) - ((w / g + h / g - 1) * g);
        return answer;
    }
}
