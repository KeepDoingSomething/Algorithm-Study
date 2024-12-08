import java.util.*;

/**
 *
 *   Author    : 전유진
 *    Date      : 2024.12.08(일)
 *     Algorithm : DFS
 *
 */
public class 메뉴_리뉴얼 {
    static Map<String, Integer> map;
    static int max = 0;

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
         solution(orders, course);


    }

    private static String[] solution(String[] orders, int[] course) {

        ArrayList<String> ans = new ArrayList<>();
        for (int c : course) {
             map = new HashMap<>();
            max = 0;
            for (String order : orders) {
                char[] strs = order.toCharArray();
                Arrays.sort(strs);
                 order =  new String(strs);
                dfs(order, "", -1, c, 0);
            }

            // map에 저장된eky
            for (String key : map.keySet()) {
                int value = map.get(key);
                if (value > 1 && max == value) {
                    ans.add(key);
                }
            }

        }
        // 정렬을 진행
        Collections.sort(ans);
        // String[] 배열로 변환
        String[] answer = ans.toArray(new String[ans.size()]);

        return answer;


    }

    private static void dfs(String order, String key, int index, int end, int depth) {

        //코스의 길이와 동일시
        if (depth == end) {
            map.put(key, map.getOrDefault(key, 0) + 1);
            max = Math.max(max, map.get(key));
        }


        for (int i = index+1; i < order.length(); i++) { //index+1을 함으로써 다음 문자만 탐색
            dfs(order, key + order.charAt(i), i, end, depth + 1);
        }

    }
}
