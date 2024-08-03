
import java.util.*;

public class Solution {

    public int solution(String str1, String str2) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        makeList(str1, list1);
        makeList(str2, list2);

        Collections.sort(list1);
        Collections.sort(list2);

        int i = 0, j = 0;
        int unionSize = 0, interSectionSize = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).equals(list2.get(j))) {
                interSectionSize++;
                unionSize++;
                i++;
                j++;
            } else if (list1.get(i).compareTo(list2.get(j)) < 0) {
                unionSize++;
                i++;
            } else {
                unionSize++;
                j++;
            }
        }

        // 남아 있는 요소 추가
        unionSize += (list1.size() - i) + (list2.size() - j);

        double ans = unionSize == 0 ? 1.0 : (double) interSectionSize / unionSize;
        return (int) (ans * 65536);
    }

    private void makeList(String str, List<String> list2) {
        for (int i = 0; i < str.length() - 1; i++) {
            String substring = str.substring(i, i + 2);
            if (containsNonAlphabetic(substring)) continue;
            list2.add(substring.toUpperCase());
        }
    }

    public boolean containsNonAlphabetic(String s) {
        return s.matches(".*[^a-zA-Z].*");
    }

}
