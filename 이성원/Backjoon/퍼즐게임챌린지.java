class Solution {
    public int solution(int[] diffs, int[] times, long limit) {


        return binarysearch(diffs, times, limit);
    }

    public int binarysearch(int[] diffs, int[] times, long limit){
        int min = 1; int max = 1000000;

        while(min <= max){
            int level = (min + max) / 2;

            long mid = calculate(diffs, times,level);

            if(mid > limit) min = level + 1;
            else max = level -1;
        }
        return  min;
    }

    public long calculate(int[] diffs, int[] times, int level){
        long aw = 0;

        for(int i =0; i < diffs.length; i++){
            if(level >= diffs[i]) aw += (long)times[i];
            else aw +=(long)(times[i] + times[i-1])* (long)(diffs[i] - level) + times[i];
        }

        return aw;
    }
}