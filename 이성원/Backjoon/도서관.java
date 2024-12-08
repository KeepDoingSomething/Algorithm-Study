import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int anw = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> plusQ = new PriorityQueue<>((p1,p2)-> p2 - p1);
        PriorityQueue<Integer> minusQ = new PriorityQueue<>((p1,p2)-> p2 - p1);


        // 큐 초기화
        for(int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());

            if(temp > 0) {
                plusQ.add(temp);
            }
            else {
                minusQ.add(Math.abs(temp));
            }
        }


        int max = 0;

        if(plusQ.isEmpty()) {
            max = minusQ.peek();
        }
        else if(minusQ.isEmpty()) {
            max = plusQ.peek();
        }
        else {
            max =  Math.max(minusQ.peek(), plusQ.peek());
        }

        while(!plusQ.isEmpty()) {
            int temp = plusQ.poll();

            for(int i = 0; i < M-1; i++) {
                plusQ.poll();
            }
            anw += temp * 2;
        }


        while(!minusQ.isEmpty()) {
            int temp = minusQ.poll();

            for(int i = 0; i < M-1; i++) {
                minusQ.poll();
            }
            anw += temp * 2;
        }



        anw -= max;


        System.out.println(anw);

    }


}
