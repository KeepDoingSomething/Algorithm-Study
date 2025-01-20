/**
 * Author    : Park Jeong Geun
 * Date      : 2025.01.20(Mon)
 * Runtime   : 104ms
 * Memory    : 14424KB
 * Algorithm : Greedy
 */

// >> 첫 번째 풀이 ( 그리디 )
// 최적의 멀티탭 교체 알고리즘을 구현하는 문제입니다. (Solve Time : 0h 28m)
// 현재 멀티탭에 꽂혀있는 전기용품 중에서, 이후에 용품을 사용할 순서가 가장 마지막인 전기용품을 먼저 교체합니다.
// 페이지 교체 알고리즘 중 OPT 알고리즘에 대해 알고 있다면 해당 아이디어를 떠올리기 쉬워집니다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Main {
	static public void main(String []args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) arr[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> multitab = new ArrayList<Integer>(); // 멀티탭을 사용 중인 전기용품 인덱스 배열
        int ans = 0;
        for (int i = 0; i < m; i++) {
            boolean used = false;
            for (int e : multitab) {
                if (e == arr[i]) { // 이미 멀티탭에 꽂혀있음!
                    used = true;
                    break;
                }
            }

            if (used) continue; // 꽂혀있으면 제외
            if (multitab.size() < n) { // 멀티탭이 아직 다 안 꽂힌 경우엔 남는 자리에 꽂는다.
                multitab.add(arr[i]);
                continue;
            }

            // 가장 오랫동안 사용하지 않을 전기용품을 교체한다.
            int not_used_num = -1;
            int not_used_term = 0;
            for (int eidx = 0; eidx < multitab.size(); eidx++) {
                for (int j = i + 1; j <= m; j++) {
                    if (j == m) { // 이후에 아예 사용하지 않는다면, 교체 1순위
                        not_used_num = eidx;
                        not_used_term = j;
                        break;
                    }
                    
                    if (multitab.get(eidx) == arr[j]) {
                        if (not_used_num == -1 || not_used_term < j) { // 더 오랫동안 사용 안할 전기용품을 교체한다.
                            not_used_num = eidx;
                            not_used_term = j;
                        } 
                        break;
                    }
                }
            }

            // 교체
            multitab.remove(not_used_num);
            multitab.add(arr[i]);
            ans += 1;
        }

        System.out.println(ans);
    }
}
