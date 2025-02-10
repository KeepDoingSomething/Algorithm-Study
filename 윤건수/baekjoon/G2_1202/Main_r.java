package baekjoon.G2_1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 보석 도둑 (Gold 2)
// https://www.acmicpc.net/problem/1202

public class Main_r {

    static int[] bagWeights;
    static Jewel[] jewels;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int jewelNum = Integer.parseInt(st.nextToken());
        int bagNum = Integer.parseInt(st.nextToken());
        jewels = new Jewel[jewelNum];
        bagWeights = new int[bagNum];

        for (int i = 0; i < jewelNum; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }

        for (int i = 0; i < bagNum; i++) {
            int bagWeight = Integer.parseInt(br.readLine());
            bagWeights[i] = bagWeight;
        }

        // 감당 가능한 무게순
        Arrays.sort(bagWeights);
        Arrays.sort(jewels, (jewelA, jewelB) -> jewelA.weight - jewelB.weight);
        PriorityQueue<Jewel> candidateJewels = new PriorityQueue<Jewel>((jewelA, jewelB) -> jewelB.value - jewelA.value);


        int jewelIdx = 0;
        long totalValue = 0;
        for(int bagWeight : bagWeights){
            while(jewelIdx < jewels.length && jewels[jewelIdx].weight <= bagWeight){
                candidateJewels.add(jewels[jewelIdx++]);
            }

            if(!candidateJewels.isEmpty()){
                totalValue += candidateJewels.poll().value;
            }
        }

        System.out.println(totalValue);
    }

    private static class Jewel{
        int weight;
        int value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "weight: " + weight + ", value: " + value;
        }
    }

}
