package programmers.LV2_92342;

import java.util.*;

public class Solution {

    final int totalPoint = 10;
    int totalArrows;
    int rionMaxTotalPoint;
    int[] apeachArrows;
    PriorityQueue<boolean[]> rionWinCase;

    public int[] solution(int n, int[] info) {
        this.totalArrows = n;
        this.apeachArrows = info;
        this.rionMaxTotalPoint = 0;
        rionWinCase = new PriorityQueue<>((case1, case2) -> {
            for(int i = case1.length - 1; i >= 0; i--) {
                if(case1[i] != case2[i]){
                    return case1[i] ? -1 : 1;
                }
            }
            return 0;
        });

        boolean[] winRion = new boolean[10];
        dfs(winRion, 0);

        int[] answer = {-1};
        if(rionMaxTotalPoint > 0){
            answer = new int[11];
            int rionArrows = 0;
            boolean[] bestRionWin = rionWinCase.poll();

            for(int i = 0; i < totalPoint; i++){
                if(bestRionWin[i]){
                    int winArrows = apeachArrows[i] + 1;
                    answer[i] = winArrows;
                    rionArrows += winArrows;
                }
            }
            answer[10] = totalArrows - rionArrows;
        }

        return answer;
    }

    private void dfs(boolean[] winRion, int winCount){

        if(winCount == 11) return;
        int rionPoint = 0;
        int apeachPoint = 0;
        for(int i = 0; i < 10; i++){
            int point = 10 - i;
            if(winRion[i]) rionPoint += point;
            else if(!winRion[i] && apeachArrows[i] > 0) apeachPoint += point;
        }

        if(rionPoint > apeachPoint && possible(winRion)){
            // System.out.println(Arrays.toString(winRion));
            if(rionPoint > rionMaxTotalPoint){
                rionWinCase.clear();
                rionMaxTotalPoint = rionPoint;
                rionWinCase.add(Arrays.copyOf(winRion, winRion.length));
            }else if(rionPoint == rionMaxTotalPoint){
                rionWinCase.add(Arrays.copyOf(winRion, winRion.length));
            }
        }

        winCount += 1;
        for(int i = 0; i < totalPoint; i++){
            if(winRion[i]) continue;
            winRion[i] = true;
            dfs(winRion, winCount);
            winRion[i] = false;
        }
    }

    private boolean possible(boolean[] winRion){
        int needArrows = 0;
        for(int i = 0; i < winRion.length; i++){
            if(winRion[i]) needArrows += apeachArrows[i] + 1;
        }

        return totalArrows <= needArrows;
    }

}