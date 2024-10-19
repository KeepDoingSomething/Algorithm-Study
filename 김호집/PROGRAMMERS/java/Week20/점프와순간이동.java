/**
 * Author    : HojipKim
 * Date      : 2024.09.27(Fri)
 * Runtime   : 0.03 ms
 * Memory    : 75.5 MB
 * Algorithm :
 */

import java.util.*;
public class Solution {
    public int solution(int n) {
        int ans = 0;

        int tmp = n;

        while(tmp != 0){
            if(tmp % 2 == 0){
                tmp /= 2;
            }else{
                tmp--;
                ans++;
            }
        }
        return ans;
    }
}