import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 *
 *   Author    : 전유진
 *   Date      : 2024.09.22(일)
 *   Algorithm : 큐
 *
 */
public class 호텔대실 {
    public static void main(String[] args) {
        String[][] book_time =
                {
                        {"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}

                };
        System.out.println(solution(book_time));
    }

    private static int solution(String[][] book_time) {
        int answer = 0;
        int[][] bookTime = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            int start = Integer.parseInt(book_time[i][0].replace(":",""));
            int end = Integer.parseInt(book_time[i][1].replace(":", ""));

            end +=10;

            if(end %100 >= 60) end +=40;
            bookTime[i][0] = start;
            bookTime[i][1] = end;
        }

        Arrays.sort(bookTime,(a1,a2)->{
            return a1[0] - a2[0];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int[] book : bookTime) {
            if (pq.isEmpty()) {
                pq.add(book);
            } else {
                int[] temp = pq.peek();
                int end = temp[1];
                if(book[0] >= end) pq.poll();
                pq.add(book);
            }
        }
        answer = pq.size();
        return answer;


    }
}
