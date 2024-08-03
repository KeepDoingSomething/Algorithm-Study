/**
 * Author    : HojipKim
 * Date      : 2024.08.03(Sat)
 * Runtime   : 35.73 ms
 * Memory    : 55 MB
 * Algorithm : 탐욕법（Greedy）
 */
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i : people){
            arr.add(i);
        }
        Collections.sort(arr, Collections.reverseOrder());

        int tmp = 0;
        int people_num = arr.size();
        for (int i = 0; i < people_num; i++) {
            if(arr.get(i) + arr.get(people_num-1) <= limit){
                answer++;
                people_num--;
            }else{
                answer++;
            }

        }

        return answer;
    }
}