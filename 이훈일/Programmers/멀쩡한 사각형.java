class Solution {
    public long solution(int w, int h) {
        double douW = (double) w;
        double douH = (double) h;
        
        long count = 0;
        for(int i = 1; i <= w; i++) {
            long start = (long) (douH / douW * (i - 1));
            long end = (long) Math.ceil(douH / douW * i);
            count += end - start;
        }
        long total = (long) w * (long) h;
        return total - count;
    }
}
