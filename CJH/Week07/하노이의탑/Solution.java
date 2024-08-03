
import java.util.ArrayList;
import java.util.List;

public class Solution {

    private List<int[]> arr = new ArrayList<>();

    public int[][] solution(int n) {
        move(n, 1, 2, 3);
        return arr.toArray(int[][]::new);
    }

    private void move(int cnt, int start, int mid, int end) {
        if (cnt == 0) return;

        move(cnt - 1, start, end, mid);
        arr.add(new int[]{start, end});
        move(cnt - 1, mid, start, end);

    }
}
