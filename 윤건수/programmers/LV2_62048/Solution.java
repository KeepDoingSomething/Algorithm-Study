package programmers.LV2_62048;

public class Solution {
    public long solution(int w, int h) {
        long total = (long)w * h;
        long cnt = 1;
        long x = 1;
        long y = 1;

        int[] dx = {1, 0, 1};
        int[] dy = {1, 1, 0};

        while(x != w || y != h){
            long left = (long)w * y;
            long right = (long)h * x;

            int direction = 0;
            if(left < right){
                direction = 1;
            }else if (left > right){
                direction = 2;
            }

            cnt++;
            x += dx[direction];
            y += dy[direction];
        }

        return total - cnt;
    }
}