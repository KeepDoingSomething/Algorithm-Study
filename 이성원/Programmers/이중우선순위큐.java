import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> (b-a));

        for(int i =0; i < operations.length; i++){
            String input = operations[i];

            String split[] = input.split(" ");

            if(split[0].equals("I")){
                pq.offer(Integer.parseInt(split[1]));
            }
            else{
                if(split[1].startsWith("-") && !pq.isEmpty()){
                    int min = Integer.MAX_VALUE;

                    for(int number: pq){
                        min = Math.min(min, number);
                    }
                    pq.remove(min);
                }
                else{
                    if(!pq.isEmpty()){
                        pq.poll();
                    }
                }
            }

        }

        int answer[] = new int[2];

        if(pq.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            answer[0] =  pq.peek();

            int min = Integer.MAX_VALUE;;

            for(int number: pq){
                min = Math.min(min, number);
            }
            answer[1] = min;
        }

        return answer;
    }
}