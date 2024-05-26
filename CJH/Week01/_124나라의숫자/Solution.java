package programmers.level2._124나라의숫자;

public class Solution {
    StringBuilder sb;

    // 풀이 1
    private void getNum(int n) {
        char[] nums = new char[]{'1', '2', '4'};
        while (n > 0) {
            sb.insert(0, nums[--n % 3]);
            n = n / 3;
        }
    }

    // 풀이 2
    private void getNum1(int n) {
        if (n == 1) {
            sb.append("1");
        } else if (n == 2) {
            sb.append("2");
        } else if (n == 3 || n == 0) {
            sb.append("4");
        } else {
            getNum((n - 1) / 3);
            getNum(n % 3);
        }
    }

    public String solution(int n) {
        sb = new StringBuilder();
        getNum(n);
        return sb.toString();
    }
}
