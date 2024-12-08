import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 후보키 {
    static int answer;
    static int n,m;
    static List<HashSet<Integer>> candidateKey;
    static String[][] relationCopy;
    public static void main(String[] args) {
        String[][] relation = {
                {"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}
                , {"300", "tube", "computer", "3"}
                , {"400", "con", "computer", "4"}
                , {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };

        solution(relation);
    }

    private static void solution(String[][] relation) {
        relationCopy = relation;
        answer = 0;

        candidateKey = new ArrayList<>();
        n = relationCopy.length;
        m = relationCopy[0].length;

        //1~m까지 사이즈만큼 조합 생성
        for (int i = 1; i < m+1 ; i++) {
            combination(0, i, 0, new HashSet<>());
        }
    }

    private static void combination(int start, int size, int depth, HashSet<Integer> set) {

        // 조합이 만들어지면
        if (depth == size) {
            //유일성 검사
            if (!unique(set)) {
                return;
            }
            //최소성 검사
            for (HashSet<Integer> key : candidateKey) {
                if (set.containsAll(key)) {
                    return;
                }
            }

            candidateKey.add(set);
            answer++;
            return;
        }


        for (int i = start;i <m;i++){
            HashSet<Integer> newSet = new HashSet<>(set);
            newSet.add(i);
            start++;
            combination(start, size, depth + 1, newSet);
        }

    }

    private static boolean unique(HashSet<Integer> set) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int idx : set) {
                sb.append(relationCopy[i][idx]);
            }
            if (!list.contains(sb.toString())) {
                list.add(sb.toString());
            } else {
                return false;
            }
        }
        return true    ;
    }
}

