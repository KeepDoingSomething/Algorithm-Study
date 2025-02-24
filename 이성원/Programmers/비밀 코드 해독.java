import java.util.*;
class Solution {

    static int answer = 0;
    static boolean visit[];

    static List<Integer> list;

    public int solution(int n, int[][] q, int[] ans) {

        visit = new boolean[n+1];

        conbination(q, ans, n, 1, 0);


        return answer;
    }

    public void conbination(int [][] q, int[] ans, int n, int start, int cnt){


        if(cnt == 5){ // 10C5 5개의 숫자를 뽑음
            list = new ArrayList<>();

            for(int i =1; i <=n; i++){
                if(visit[i]){
                    list.add(i);
                }
            }

            for(int i =0; i < q.length; i++){
                int count =0;
                for(int j =0; j < q[i].length; j++){
                    int number = q[i][j];

                    if(list.contains(number)){
                        count++;
                    }
                }

                if(ans[i] != count){
                    return;
                }

            }
            answer++;
            return;

        }


        for(int i = start; i <=n; i++){ // 조합
            visit[i] = true;
            conbination(q,ans,n, i+1, cnt +1);
            visit[i] = false;
        }
    }


}