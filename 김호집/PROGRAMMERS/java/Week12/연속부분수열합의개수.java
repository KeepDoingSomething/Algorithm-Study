/*
 * Author    : HojipKim
 * Date      : 2024.08.08(Thu)
 * Runtime   : 1379.08 ms
 * Memory    : 77.3 MB
 * Algorithm :（Greedy）
* */

class Solution {

    private boolean[] arr = new boolean[1000001];
    public int solution(int[] elements) {
        int answer = 0;

        for (int i = 1; i < elements.length + 1; i++) {
            for (int j = 0; j < elements.length; j++) {
                int num=0;
                for (int k = j; k < j+i; k++) {
                    num += elements[k % (elements.length)];
                }
                arr[num] = true;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]){
                answer++;
            }
        }
        return answer;
    }
}