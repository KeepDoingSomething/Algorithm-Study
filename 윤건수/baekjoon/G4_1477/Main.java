package baekjoon.G4_1477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int restCount = Integer.parseInt(params[0]);
        int targetCreateRest = Integer.parseInt(params[1]);
        int roadLength = Integer.parseInt(params[2]);
        String rest = br.readLine();
        List<Integer> restPoint = restCount == 0
                ? new ArrayList<>()
                : Arrays.stream(rest.split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

        restPoint.add(0);
        restPoint.add(roadLength);
        Collections.sort(restPoint);

        int sectionMin = findSectionMin(restPoint, targetCreateRest);
        System.out.println(sectionMin);
    }

    private static int findSectionMin(List<Integer> restPoint, int targetCreateRest) {
        int left = 1;
        int right = restPoint.get(restPoint.size() - 1);

        int sectionMin = 0;
        while (left <= right) {
            int restInterval = (left + right) / 2;
            int totalCreateRest = 0;
            for(int i = 0; i < restPoint.size() - 1; i++){
                int sectionFrom = restPoint.get(i);
                int sectionTo = restPoint.get(i + 1);
                int sectionInterval = sectionTo - sectionFrom;
                totalCreateRest += sectionInterval / restInterval;
                if(sectionInterval % restInterval == 0) totalCreateRest--;
            }

            if(totalCreateRest > targetCreateRest){
                left = restInterval + 1;
            }else{
                right = restInterval - 1;
                sectionMin = restInterval;
            }
        }

        return sectionMin;
    }

}
