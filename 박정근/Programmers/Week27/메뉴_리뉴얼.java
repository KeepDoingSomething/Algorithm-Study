/**
 * Author    : Park Jeong Geun
 * Date      : 2024.12.7(Sat)
 * Runtime   : 2762.73ms
 * Memory    : 104MB
 * Algorithm : Bitmask + Brute force
 */

// >> 첫 번째 풀이 ( 비트마스킹 + 완전 탐색 )
// 비트마스킹으로 풀이했습니다. (Solve Time : 0h 24m)
// 처음엔 주문하는 "사람"을 기준으로 비트마스킹 순회를 해서 코스요리 조합을 골라냈는데, 이때 여러 사람들이 동일하게 주문한 요리 안에서 또 다른 조합이 필요한 경우를 구해야하는 번거로움이 있었습니다.
// Ex. 1번 손님과 2번 손님이 A, B, C, D, E 를 동시에 주문했다면, 사람을 기준으로 순회했을 때 단품메뉴 개수가 2인 코스요리를 찾으려면 또 순회해야 함.
// 이 때문에, 사람 대신 알파벳 A부터 Z까지를 비트마스킹 순회하면서, 해당 조합을 몇 명의 사람이 주문했는지를 구해 일괄적으로 저장하고, 가장 많이 시킨 코스요리 조합을 구했습니다.

import java.util.Arrays;
import java.util.ArrayList;

class Solution {    
    public int countBit(int a) {
        int res = 0;
        while (a > 0) {
            res += a % 2;
            a /= 2;
        }
        return res;
    }
    public String[] solution(String[] orders, int[] course) {
        // 1. 1 ~ (1 << 26) 까지 순회 (비트 1개는 제외)
        // 2. 주문한 사람 많은 순으로 코스요리 개수 별 등록
        // 3. course 배열대로 result 만들고 정렬
        
        // 코스요리 개수 별, 주문한 사람이 가장 많은 코스요리 정보를 저장하는 배열
        ArrayList<Integer>[] max_course = new ArrayList[11];
        for (int i = 0; i < 11; i++) max_course[i] = new ArrayList<Integer>();
        
        // 코스요리 개수 별, 주문한 사람이 가장 많은 코스요리가 몇 번 주문 받았는지를 저장하는 배열
        int[] max_course_calls = new int[11];
        Arrays.fill(max_course_calls, 0);
        
        int n = orders.length;
        // 주문 정보
        int[] orders_i = new int[n];
        for (int i = 0; i < n; i++) {
            // 비트마스킹 순회를 위해, 문자열 주문 정보를 정수로 변경 
            int now_order = 0;
            for (int j = 0; j < orders[i].length(); j++)
                now_order += (1 << ((int)orders[i].charAt(j) - (int)'A'));
            
            orders_i[i] = now_order;
        }
        
        for (int i = 3; i < (1 << 26); i++) { // 3은 2진수로 11. 단품메뉴 2개 코스요리이기 때문에 3부터 시작했습니다. 0부터 시작해도 큰 상관 없지만 그냥 해봤습니다..
            int bits = countBit(i);
            // 비트 수를 세서, 단품메뉴 개수가 2 이상 10 이하가 아니라면 패스
            if (bits < 2 || bits > 10) continue;

            // 몇 명의 사람이 이 조합을 주문했는가?
            int calls = 0;
            for (int p = 0; p < n; p++) {
                if ((i & orders_i[p]) == i) calls += 1;
            }

            // 2명 이상 주문했고, 동일한 개수의 다른 조합보다 더 많이 혹은 같게 주문되었다면
            if (calls >= 2 && max_course_calls[bits] <= calls) {
                if (max_course_calls[bits] < calls) {
                    // 더 많이 주문되었다면 초기화
                    max_course_calls[bits] = calls;
                    max_course[bits].clear();
                }
                // 더 많이 혹은 같게 주문되었다면 추가
                max_course[bits].add(i);
            }
        }

        // 정답 스택
        ArrayList<String> answer_stack = new ArrayList<String>();
        for (int c : course) { // course 배열에서 지정된 개수의 조합만 정답 배열에 추가
            for (int i = 0; i < max_course[c].size(); i++) {
                String now_course = "";
                for (int mask = 1, idx = 0; mask < (1 << 26); mask <<= 1, idx += 1) {
                    // 정수 조합 정보를 문자열로 변환
                    if ((max_course[c].get(i) & mask) != 0) {
                        now_course += (char)(((int)'A') + idx);
                    }
                }
                answer_stack.add(now_course);
            }
        }
        
        String[] answer = new String[answer_stack.size()];
        for (int i = 0; i < answer_stack.size(); i++) answer[i] = answer_stack.get(i);
        // 오름차순 정렬
        Arrays.sort(answer);
        
        return answer;
    }
}
