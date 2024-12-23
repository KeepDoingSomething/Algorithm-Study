/**
 * Author    : Kang San Ah
 * Date      : 2024.12.22(Sun)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : hash map
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class 귤고르기 {
    public int solution(int k, int[] tangerine) {
        int answer = 0; int sum = 0; int cnt = 0;
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int num : tangerine){
            map.put(num, map.getOrDefault(num,0)+1);
        }

        ArrayList<Integer> arr = new ArrayList<>(map.values());
        Collections.sort(arr, Collections.reverseOrder()); // 내림차순으로 정렬

        for (int x: arr
        ) {
            if (sum + x >= k){
                cnt++;
                break;
            }else{
                sum += x;
                cnt++;
            }
        }

        answer = cnt;
        return answer;
    }
}
