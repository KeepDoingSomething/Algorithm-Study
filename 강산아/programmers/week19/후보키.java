/**
 * Author    : Kang San Ah
 * Date      : 2024.10.12(Sat)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : 구현
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {
    List<Integer> answer = new ArrayList<>();

    public int solution(String[][] relation) {
        int n = relation.length;
        int m = relation[0].length;

        for (int i = 1; i < 1 << m; i++) {
            Set<String> s = new HashSet<>();

            for(int j = 0; j < n; j++){
                String tmp = "";

                for(int k = 0; k < m; k++){
                    if((i & 1 << k) > 0){
                        tmp += (relation[j][k]);
                    }
                }
                s.add(tmp);
            }
            //유일성을 만족하고 최소성을 만족한다면
            if(s.size() == n && check(i)){
                answer.add(i);
            }
        }
        return answer.size();
    }
    boolean check(int i) {
        for (int j : answer) {
            // i & j == j의 의미는 i가 j의 부분집합인지에 대한 여부를 묻는 것.
            if ((i & j) == j) return false;
        }
        return true;
    }
}
