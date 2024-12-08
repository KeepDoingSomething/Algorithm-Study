/**
 * Author    : Kang San Ah
 * Date      : 2024.12.05(Thu)
 * Runtime   : 1 sec
 * Memory    : 256 MB
 * Algorithm : dfs
 */


import java.util.*;

public class 메뉴리뉴얼 {
    static HashMap<String, Integer> hm = new HashMap<>();
    static ArrayList<String> answerList = new ArrayList<>();

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        for (int i : course) {
            for (String order : orders) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars);

                dfs(0, "", chars, i);
            }

            if (!hm.isEmpty()) {
                List<Integer> countList = new ArrayList<>(hm.values());
                int max = Collections.max(countList);

                if (max > 1) {
                    for (String key : hm.keySet()) {
                        if (hm.get(key) == max) {
                            answerList.add(key);
                        }
                    }
                }
            }
            hm.clear();
        }

        Collections.sort(answerList);
        answer = new String[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    static void dfs(int depth, String course, char[] chars, int i) {
        if (course.length() == i) {
            if (hm.containsKey(course)) {
                hm.put(course, hm.get(course) + 1);
            }else{
                hm.put(course, 1);
            }
            return;
        }

        if (depth >= chars.length) {
            return;
        }

        dfs(depth + 1, course + chars[depth], chars, i);
        dfs(depth + 1, course, chars, i);
    }
}
