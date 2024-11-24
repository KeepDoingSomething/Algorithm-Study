/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.15(Fri)
 * Runtime   : 48.55ms
 * Memory    : 156MB
 * Algorithm : Stack
 */

// >> 첫 번째 풀이 ( 스택 )
// 보조 컨테이너 벨트를 스택을 이용해서 구현하면 간단히 풀리는 문제입니다.
// 주 컨테이너 벨트에서 원하는 순서의 상자를 찾을 때 까지 보조 컨테이너 벨트에 넣습니다.
// 주 컨테이너 벨트에서 찾기 전 보조 컨테이너 벨트 상단에 있는지도 확인해야 합니다.
// 입력으로 들어온 order 는, 몇 번째 상자가 컨테이너 벨트의 어디에 있는지 정확히 알 수 있게끔 가공을 해야 합니다.

import java.util.Stack;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int n = order.length;
        int[] main_container = new int[order.length];
        for (int i = 0; i < n; i++) // 컨테이너 벨트의 order[i] - 1 번째 상자는 i + 1 번째로 실어야 하는 상자.
            main_container[order[i] - 1] = i + 1; 
        
        Stack<Integer> sub_container = new Stack<>();
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (sub_container.size() > 0 && sub_container.peek() == i + 1) { 
                // 보조 컨테이너 벨트의 상단에 찾고자 하는 상자가 있는가?
                sub_container.pop();
                answer += 1;
            }
            else {
                while (idx < main_container.length && main_container[idx] != i + 1) {
                    // 찾고자 하는 상자를 찾을 때 까지 보조 컨테이너 벨트에 넣기
                    sub_container.push(main_container[idx]);
                    idx += 1;
                }

                if (idx < main_container.length && main_container[idx] == i + 1) {
                    // 주 컨테이너 벨트에서 찾았는가?
                    answer += 1;
                    idx += 1;
                }
                else {
                    // 주, 보조 어디에서도 찾지 못했으므로 종료
                    break;
                }
            }
        }
        
        
        return answer;
    }
}
