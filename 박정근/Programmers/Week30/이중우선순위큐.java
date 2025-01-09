/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.08(Wed)
 * Runtime   : 48.74ms
 * Memory    : 124MB
 * Algorithm : Priority Queue
 */

// >> 첫 번째 풀이 ( 우선순위 큐 )
// 우선순위 큐로 풀이했습니다. NullPointerException 때문에 20분을 날렸네요.. (Solve Time : 0h 30m)
// 최솟값 우선순위 큐와 최댓값 우선순위 큐에 동시에 같은 값을 넣어서, 최솟값 / 최댓값을 자유롭게 뺄 수 있습니다.
// 최솟값 우선순위 큐에서 뺀 값이 최댓값 우선순위 큐에는 그대로 남아있다거나, 최댓값 우선순위 큐에서 뺀 값이 최솟값 우선순위 큐에는 그대로 남아있을 수 있습니다.
// -> 각각 삭제 큐를 추가로 두어서, 최솟값(최댓값) 을 빼기 전에 다른 큐에서 이미 뺀 값이라면 계속해서 지울 수 있도록 처리했습니다.

// 다른 사람의 풀이를 보니 remove() 로 특정 순서? 값?을 지울 수도 있군요.
// 개인적인 의견이지만 힙의 특성 상 peek 외에는 정렬되어 있지 않기 때문에, 특정 값을 위해 힙 트리를 탐색하여 뽑는 코드보다는 우선순위 큐를 2개 더 사용해 동기화가 바로바로 되는 제 코드가 성능이 더 좋을겁니다. 
// 실제로 어느 코드 가져와서 돌려보니 적은 데이터에서는 다른 사람 코드가 더 유리하거나 비슷하지만, 큰 데이터에서는 제 코드가 1.5배 ~ 2배정도 더 빠르네요.

import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int[] solution(String[] operations) {
        // 1. 최솟값 큐 : 큐에서 최솟값을 가져오는 우선순위 큐
        // 2. 최솟값 삭제 큐 : 최댓값 큐에서 삭제된 값이 있다면 저장한 뒤, 삭제된 값들 중 최솟값을 가져오는 우선순위 큐
        // 3. 최댓값 큐 : 큐에서 최댓값을 가져오는 우선순위 큐
        // 4. 최댓값 삭제 큐 : 최솟값 큐에서 삭제된 값이 있다면 저장한 뒤, 삭제된 값들 중 최댓값을 가져오는 우선순위 큐
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(); 
        PriorityQueue<Integer> minDelPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> maxDelPQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String s : operations) {
            if (s.charAt(0) == 'I') { // I 숫자 : 큐에서 주어진 숫자 삽입
                int now = Integer.parseInt(s.substring(2));

                // 최솟값 큐, 최댓값 큐에 동시에 넣습니다.
                minPQ.add(now); maxPQ.add(now);
            }
            else {
                if (s.charAt(2) == '1') { // D 1 : 큐에서 최댓값 삭제
                    // 최댓값 큐에서 최댓값을 빼기 전, 이미 최솟값 큐에서 뺀 값이 있는지 확인
                    while ((!maxDelPQ.isEmpty()) && (!maxPQ.isEmpty())) {
                        if (maxDelPQ.peek().equals(maxPQ.peek())) {
                            maxDelPQ.remove();
                            maxPQ.remove();
                        }
                        else break;
                    }
                    if (!maxPQ.isEmpty()) {
                        int now = maxPQ.poll();
                        // 최댓값 큐에서 뺀 값은, 최솟값 큐에서도 알 수 있도록 최솟값 삭제 큐에 넣습니다.
                        // 최솟값 삭제 큐는 삭제한 값 중 최솟값을 바라보기 때문에, 최솟값 큐의 peek가 지워졌다면 바로 알 수 있습니다.
                        minDelPQ.add(now);
                    }
                }
                else { // D -1 : 큐에서 최솟값 삭제
                    // 최솟값 큐에서 최솟값을 빼기 전, 이미 최댓값 큐에서 뺀 값이 있는지 확인
                    while ((!minDelPQ.isEmpty()) && (!minPQ.isEmpty())) {
                        if (minDelPQ.peek().equals(minPQ.peek())) {
                            minDelPQ.remove();
                            minPQ.remove();
                        }
                        else break;
                    }
                    if (!minPQ.isEmpty()) {
                        int now = minPQ.poll();
                        // 최솟값 큐에서 뺀 값은, 최댓값 큐에서도 알 수 있도록 최댓값 삭제 큐에 넣습니다.
                        // 최댓값 삭제 큐는 삭제한 값 중 최댓값을 바라보기 때문에, 최댓값 큐의 peek가 지워졌다면 바로 알 수 있습니다.
                        maxDelPQ.add(now);
                    }
                }
            }
        }
        
        // 마지막 최댓값 / 최솟값을 알기 전에 최댓값 큐 / 최솟값 큐 둘다 동기화 해줍니다.
        while ((!maxDelPQ.isEmpty()) && (!maxPQ.isEmpty())) {
            if (maxDelPQ.peek().equals(maxPQ.peek())) {
                maxDelPQ.remove();
                maxPQ.remove();
            }
            else break;
        }
        while ((!minDelPQ.isEmpty()) && (!minPQ.isEmpty())) {
            if (minDelPQ.peek().equals(minPQ.peek())) {
                minDelPQ.remove();
                minPQ.remove();
            }
            else break;
        }
        int[] answer = {(maxPQ.isEmpty()) ? 0 : maxPQ.poll(), (minPQ.isEmpty()) ? 0 : minPQ.poll()};
        return answer;
    }
}
