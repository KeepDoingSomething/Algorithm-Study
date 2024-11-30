/**
 * Author    : Park Jeong Geun
 * Date      : 2024.11.18(Mon)
 * Runtime   : 287.20 ms
 * Memory    : 183MB
 * Algorithm : Prefix Sum + Brute force
 */

// >> 첫 번째 풀이 ( 누적 합 + 완전 탐색 )
// 4가지 항목마다 점수를 기준으로 누적 합을 만들었습니다. (5차원 배열 누적합)
// 3 x 2 x 2 x 2 x 100000 만큼의 공간을 만들어야 하지만, 시간 안에는 해결할 수 있습니다.
// 공간 복잡도를 이만큼 잡아먹는건 아닌 것 같아서 다른 방법을 떠올려 보려 했는데, 이게 저한테는 가장 원활한 방법으로 보이네요..

import java.util.StringTokenizer;
class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 누적 합 배열 info_sum
        // info_sum[i][j][k][l][p] : 개발언어가 i고, 직군이 j고, 경력이 k고, 소울푸드가 l이고 점수가 p 이상인 사람의 수
        int[][][][][] info_sum = new int[3][3][3][3][100001];
        
        for (String s : info) {
            StringTokenizer st = new StringTokenizer(s);
            
            int[] idx = new int[4];
            for (int c = 0; c < 4; c++) {
                String now = st.nextToken();    
                
                switch(now) {
                    case "cpp":
                    case "backend":
                    case "junior":
                    case "chicken":
                        idx[c] = 0;
                        break;
                    case "java":
                    case "frontend":
                    case "senior":
                    case "pizza":
                        idx[c] = 1;
                        break;
                    case "python":
                        idx[c] = 2;
                        break;
                }
            }
            
            info_sum[idx[0]][idx[1]][idx[2]][idx[3]][Integer.parseInt(st.nextToken())] += 1;
        }
        
        // 누적 합 만들기 (특정 항목에서 X점 이상인 사람의 수)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int sc = 99999; sc >= 0; sc--) {
                            info_sum[i][j][k][l][sc] += info_sum[i][j][k][l][sc+1];
                        }
                    }
                }
            }
        }
        
        int q_idx = 0;
        for (String q : query) {
            StringTokenizer st = new StringTokenizer(q);
            
            int[] idx_start = new int[4];
            int[] idx_end = {3, 2, 2, 2};
            for (int c = 0; c < 4; c++) { 
                String now;
                if (c != 0) now = st.nextToken(); // 중간에 있는 and 빼기
                now = st.nextToken();
                
                switch(now) {
                    case "cpp":
                    case "backend":
                    case "junior":
                    case "chicken":
                        idx_start[c] = 0;
                        idx_end[c] = 1;
                        break;
                    case "java":
                    case "frontend":
                    case "senior":
                    case "pizza":
                        idx_start[c] = 1;
                        idx_end[c] = 2;
                        break;
                    case "python":
                        idx_start[c] = 2;
                        idx_end[c] = 3;
                        break;
                    case "-":
                        idx_start[c] = 0;
                        break;
                }
            }
        
            int sc = Integer.parseInt(st.nextToken()); // 점수 조건
            int res = 0;
            
            // -1 이면 전체를 탐색할 수 있도록 했습니다. 
            // 복잡해 보이지만, 한 쿼리마다 최대 3 x 2 x 2 x 2번의 순회로 답을 얻을 수 있습니다.
            for (int i = idx_start[0]; i < idx_end[0]; i++) {
                for (int j = idx_start[1]; j < idx_end[1]; j++) {
                    for (int k = idx_start[2]; k < idx_end[2]; k++) {
                        for (int l = idx_start[3]; l < idx_end[3]; l++) {
                            res += info_sum[i][j][k][l][sc];
                        }
                    }      
                }
            }
            
            
            answer[q_idx++] = res;
        }
        return answer;
    }
}
