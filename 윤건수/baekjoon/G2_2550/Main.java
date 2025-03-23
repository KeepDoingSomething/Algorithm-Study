package baekjoon.G2_2550;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int maxBulbCnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt =  Integer.parseInt(br.readLine());
        List<Integer> switchNums = stringLineToIntegerList(br.readLine());
        List<Integer> bulbNums = stringLineToIntegerList(br.readLine());
        int[] switchBulbSeq = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            int switchNum = switchNums.get(i);
            int seq = bulbNums.indexOf(switchNum);
            switchBulbSeq[i] = seq;
        }


        maxBulbCnt = 1;
        int[] lis = new int[cnt];
        int[] positions = new int[cnt];
        Arrays.fill(lis, Integer.MAX_VALUE);
        for(int i = 0; i < cnt; i++){
            int lisIdx = binarySearchForLIS(lis, switchBulbSeq[i]);
            lis[lisIdx] = switchBulbSeq[i];
            maxBulbCnt = Math.max(maxBulbCnt, lisIdx + 1);
            positions[i] = lisIdx;
        }

        int findSeq = maxBulbCnt - 1;
        List<Integer> result = new ArrayList<>();
        for(int i = cnt - 1; i >= 0; i--){
            if(positions[i] == findSeq){
                result.add(switchNums.get(i));
                findSeq--;
            }
        }

        String resultBulbs = result.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(maxBulbCnt);
        System.out.println(resultBulbs);
    }

    private static List<Integer> stringLineToIntegerList(String readLine){
        return Arrays.stream(readLine.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static int binarySearchForLIS(int[] lis, int seqNum){

        int left = 0;
        int right = maxBulbCnt - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right)/ 2;
            int compareSeq = lis[mid];
            if (seqNum > compareSeq) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

}
