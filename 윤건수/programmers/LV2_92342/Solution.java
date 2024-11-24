package programmers.LV2_92342;

import java.util.*;

public class Solution {

    final int totalPoint = 10;
    int totalArrows;
    int maxPointDiff;
    int[] apeachArrows;
    PriorityQueue<boolean[]> rionWinCase;

    public int[] solution(int n, int[] info) {
        this.totalArrows = n;
        this.apeachArrows = info;
        this.maxPointDiff = 0;
        rionWinCase = new PriorityQueue<>((case1, case2) -> {
            for(int i = case1.length - 2; i >= 0; i--) {
                if(case1[i] != case2[i] && case1[i]){
                    return 1;
                }
            }
            return 0;
        });

        boolean[] winRion = new boolean[11];
        dfs(winRion, 0);

        int[] answer = {-1};
        if(maxPointDiff > 0){
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

    private void dfs(boolean[] winRion, int depth){
        int rionPoint = 0;
        int apeachPoint = 0;
        for(int i = 0; i < 10; i++){
            int point = 10 - i;
            if(winRion[i]) rionPoint += point;
            else if(!winRion[i] && apeachArrows[i] > 0) apeachPoint += point;
        }

        if(rionPoint > apeachPoint && possible(winRion)){
            int pointDiff = rionPoint - apeachPoint;

            if(pointDiff > maxPointDiff){
                rionWinCase.clear();
                maxPointDiff = pointDiff;
                rionWinCase.add(Arrays.copyOf(winRion, winRion.length));
            }else if(pointDiff == maxPointDiff){
                rionWinCase.add(Arrays.copyOf(winRion, winRion.length));
            }
        }

        depth += 1;
        if(depth == 11) return;
        winRion[10 - depth] = true;
        dfs(winRion, depth);
        winRion[10 - depth] = false;
        dfs(winRion, depth);

    }

    private boolean possible(boolean[] winRion){
        int needArrows = 0;
        for(int i = 0; i < winRion.length; i++){
            if(winRion[i]) needArrows += apeachArrows[i] + 1;
        }

        return totalArrows >= needArrows;
    }

}