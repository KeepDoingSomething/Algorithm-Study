/**
 * Author    : 김현정
 * Date      : 2024.09.05(목)
 * Runtime   :
 * Memory    :
 * Algorithm : Queue - LV2_42583 다리를 지나는 트럭
 */

package queue;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;

        for(int i = 0; i < truck_weights.length; i++) {
            int truck = truck_weights[i];

            while(true) {
                if(queue.isEmpty()) {
                    queue.add(truck_weights[i]);
                    answer++;
                    sum+=truck;
                    break;
                }else if(queue.size() == bridge_length) {
                    sum -= queue.poll();
                }else {
                    if( sum + truck <= weight) {
                        queue.add(truck);
                        sum+= truck;
                        answer++;
                        break;
                    }else {
                        queue.add(0);
                        answer++;
                    }
                }
            }

        }


        return answer + bridge_length;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7,4,5,6};
        int answer = solution.solution(bridge_length, weight, truck_weights);
        System.out.println(answer);
    }
}
