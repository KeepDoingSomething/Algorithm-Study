/**
 * Author    : HojipKim
 * Date      : 2024.09.07(Sat)
 * Runtime   : 1681 ms
 * Memory    : 409 MB
 * Algorithm : Queue
 */

import java.util.Queue;
import java.util.ArrayDeque;

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.solution(new int[]{1, 1}, new int[]{1, 5});
        System.out.println(ans);

    }
}

class Solution {

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        long sum = addAllElementTwoArray(queue1, queue2);

        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        if(sum == -1 || !isEvenNumber(sum)){
            return -1;
        }

        long sum1 = addAllElement(queue1);
        long sum2 = addAllElement(queue2);
        if(sum1 + sum2 == 0){
            return 0;
        }

        int q2Res = 0;
        if(!(arrayToQueue(queue1, q1) && arrayToQueue(queue2, q2))){
            return -1;
        }

        q2Res = startOrderByQ2(q1, q2, sum1, sum2);

        if(q2Res == -1){
            return -1;
        }
        answer = q2Res;

        return answer;
    }

    public static int startOrderByQ2(Queue<Integer> q1, Queue<Integer> q2, long sum1, long sum2){
        int cnt = 0;

        while(sum1 != sum2){
            if(cnt > 100_000_000){
                return -1;
            }
            if(sum1 > sum2){
                int tmp = q1.poll();
                q2.offer(tmp);
                sum1 -= tmp;
                sum2 += tmp;
                cnt++;
            }else if(sum1 < sum2){
                int tmp = q2.poll();
                q1.offer(tmp);
                sum1 += tmp;
                sum2 -= tmp;
                cnt++;
            }else{
                break;
            }

        }
        return cnt;
    }

    public static long addAllElement(int[] arr){
        long sum = 0;

        for(long element : arr){
            sum += element;
        }

        return sum;
    }

    public static boolean arrayToQueue(int[] arr, Queue<Integer> queue){
        for(int elem : arr){
            queue.offer(elem);
        }
        if(queue.size() == arr.length){
            return true;
        }

        return false;
    }

    public static long addAllElementTwoArray(int[] arr1, int[] arr2){

        long sum = 0;

        for(int i = 0; i < arr1.length; i++){
            sum += arr1[i] + arr2[i];
        }

        if((arr1.length != arr2.length)){
            return -1;
        }

        return sum;

    }

    public static boolean isEvenNumber(long num){

        if(num % 2 ==  0){
            return true;
        }

        return false;
    }
}