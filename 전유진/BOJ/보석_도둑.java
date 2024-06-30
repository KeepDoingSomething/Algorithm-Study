import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * Author    : Jeon Yu Jin
 * Date      : 2024.06.21(Fri)
 * Runtime   :  KB
 * Memory    :  ms
 * Algorithm : 구현
 * X
 */

public class 보석_도둑 {
    static class Jewlery{
        int mess;
        int value;

        public Jewlery(int mess, int value) {
            this.mess = mess;
            this.value = value;
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(" ");
        int num = Integer.parseInt(split[0]);
        int bag = Integer.parseInt(split[1]);
        Jewlery[] jewelries = new Jewlery[num];
        for (int i = 0; i < num; i++) {
            String[] split1 = sc.nextLine().split(" ");
            int m = Integer.parseInt(split1[0]);
            int v = Integer.parseInt(split1[1]);
            jewelries[i] = new Jewlery(m, v);
        }

        Arrays.sort(jewelries,(o1,o2)-> {
            if(o1.mess == o2.mess) return o2.value - o1.value;
            return o1.mess - o2.mess;
        });

        int[] weight = new int[bag];
        for (int i = 0; i < bag; i++) {
            weight[i] = sc.nextInt();
        }

        Arrays.sort(weight);
        int total = 0;
        int j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i <bag; i++) {

            while (j < num && jewelries[j].mess <= weight[i]) {
                pq.add(jewelries[j].value);
                j++;
            }
            if (!pq.isEmpty()) {
                total += pq.poll();
            }

        }

        System.out.println("total = " + total);


    }
}
