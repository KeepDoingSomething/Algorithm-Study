/**
 * Author    : Kang San Ah
 * Date      : 2024.11.27(Wed)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : Queue
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 기능개발 {

    public int[] solution(int[] progresses, int[] speeds) {

        int[] answer = {};
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0 ; i < progresses.length ; i++){
            int num = ((100-progresses[i]) / speeds[i]);
            if ((100-progresses[i]) % speeds[i] == 0)queue.add(num);
            else queue.add(num+1);
        }

        List<Integer> list = new ArrayList<>();
        int prior = queue.poll();
        int cnt = 1;

        while (!queue.isEmpty()){
            if (prior >= queue.peek()){
                cnt ++;
                queue.poll();
            }else {
                list.add(cnt);
                cnt = 1;
                prior = queue.poll();
            }
        }
        list.add(cnt);
        answer = list.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
