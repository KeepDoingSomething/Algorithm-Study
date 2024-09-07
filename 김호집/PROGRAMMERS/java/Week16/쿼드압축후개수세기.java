/**
 * Author    : HojipKim
 * Date      : 2024.09.07(Sat)
 * Runtime   : 25.74 ms
 * Memory    : 115 MB
 * Algorithm : Divide and Conquer
 */

import java.util.Arrays;

class Solution {

    public static int[] ans = new int[2];

    public int[] solution(int[][] arr) {
        int[] answer = {};

        divide_conquer(arr, 0, 0, arr.length);

        answer = ans;
        return answer;
    }

    public void divide_conquer(int[][] arr, int x, int y, int size){
        boolean flag = false;
        int tmp = arr[y][x];
        for(int j = y; j < y+size; j++){
            for(int i = x; i < x+size; i++){
                if(tmp != arr[j][i]){
                    flag = true;
                    break;
                }
            }
        }
        if(flag){
            divide_conquer(arr, x , y, size/2);
            divide_conquer(arr, x+size/2, y, size/2);
            divide_conquer(arr, x, y+size/2, size/2);
            divide_conquer(arr, x+size/2, y+size/2, size/2);
        }else{
            ans[tmp]++;
            return;
        }

    }
}