import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-18
 * Runtime          :   144.01 ms
 * Memory           :   90.3 MB
 * Used algorithm   :   Greedy, Heap
 */
class Solution {
    public long solution(int n, int[] works) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                Collections.reverseOrder()
        );

        Arrays.stream(works).forEach(pq::add);

        while (!pq.isEmpty() && n-- > 0)    {
            int next = pq.poll() - 1;
            if (next > 0)   {
                pq.add(next);
            }
        }

        return pq.stream()
                .mapToLong(r -> (long) r * r)
                .sum();
    }
}
