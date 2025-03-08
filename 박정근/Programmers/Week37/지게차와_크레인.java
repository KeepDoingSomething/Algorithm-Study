/**
 * Author    : Park Jeong Geun
 * Date      : 2025.03.08(Sat)
 * Runtime   : 6.67ms
 * Memory    : 92.7MB
 * Algorithm : Implementation
 */

// >> 첫 번째 풀이 ( 구현 )
// 단순 구현 문제입니다. (Solve Time : 2h 19m)
// 지게차와 크레인 유형에 따라 다르게 컨테이너를 뺀 뒤 남은 컨테이너의 개수를 출력하면 되는 문제입니다.
// 중점으로 두었던 부분은, "입력으로 들어오는 storage (50x50) 의 작은 사이즈를 전부 탐색하지 않고 풀이하자" 였습니다.

// 1. 알파벳 26가지에 따라 각각 ArrayList 를 만들고 특정 알파벳 컨테이너마다 위치 좌표를 저장했습니다.
// 요청이 들어오면 특정 알파벳 ArrayList 만 탐색하여 탐색 횟수를 줄였습니다.

// 2. 가장자리 컨테이너부터 시작해서, 하나씩 뺐을 때 주변 컨테이너가 가장자리에 닿았다고 처리하는 과정을 setEdgeQ 와 noEdgeQ 로 처리했습니다. 
// (setEdgeQ) 컨테이너를 빼는 동시에 가장자리에 닿았다고 처리해버리면 바로 다음 컨테이너 처리 연산에 영향을 주기에 이를 방지하고자 나중에 처리합니다.
// (noEdeQ) 나중에서야 가장자리에 포함되서, 가장자리에 닿았다고 처리하지 못한 컨테이너들을 처리합니다.


import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class QNode {
    int y, x;
    public QNode(int y, int x) { this.y = y; this.x = x; }
}

class Solution {
    int n, m;
    public int contNum(int i, int j) { return i * m + j; } // 컨테이너의 위치 정보를 i * m + j 번으로 반환합니다.
    public int contIdx(char a) { return (int)a - (int)'A'; } // 특정 알파벳 인덱스를 얻는 함수입니다.
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        n = storage.length; m = storage[0].length();
        
        // 알파벳 별 전체 컨테이너
        ArrayList<Integer>[] cont = new ArrayList[26]; 
        for (int i = 0; i < 26; i++) cont[i] = new ArrayList<>();
        
        // 컨테이너의 상태
        // -2 : 가장자리에 포함되지 않은 빠진 컨테이너 (크레인으로 뺀 0 컨테이너)
        // -1 : 가장자리에 포함된 빠진 컨테이너
        // 0 : 가장자리에 닿지 않은 컨테이너
        // 1 : 가장자리에 닿은 컨테이너
        int[][] cont_edge = new int[n][m]; 
        for (int i = 0; i < n; i++) Arrays.fill(cont_edge[i], 0);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 가장자리를 닿음 처리 합니다.
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) cont_edge[i][j] = 1;

                // 알파벳에 따라 다른 ArrayList에 위치 정보를 저장합니다.
                cont[contIdx(storage[i].charAt(j))].add(contNum(i, j));
            }
        }
        
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        // 주변의 컨테이너가 빠졌기 때문에, 가장자리에 닿았다고 처리를 해야하는 컨테이너들의 위치 좌표 큐입니다.
        Queue<QNode> setEdgeQ = new LinkedList<>(); 
        
        // 처리를 다 하고 난 뒤에서야 -2 컨테이너가 가장자리에 포함되게 된다면, 그 주변을 가장자리 닿음 처리해야 하는 큐입니다.
        Queue<QNode> noEdgeQ = new LinkedList<>(); 
        
        for (String r : requests) {
            setEdgeQ.clear();
            noEdgeQ.clear();
            
            for (int c : cont[contIdx(r.charAt(0))]) {
                int y = c/m; int x = c%m;
                if (cont_edge[y][x] < 0) continue; // 이미 빠진 컨테이너는 스킵합니다.

                if (cont_edge[y][x] == 0) { // 가장자리에 닿지 않은 컨테이너입니다.
                    if (r.length() == 1) continue; // 지게차를 이용한다면 빼지 않습니다.

                    // 이후에 가장자리 포함 여부를 보는 컨테이너 위치 좌표 큐에 삽입합니다.
                    noEdgeQ.add(new QNode(y, x));
                    
                    cont_edge[y][x] = -2;
                    answer += 1;
                    continue;
                }
                
                // 가장자리에 닿은 컨테이너는, 주변을 닿았다고 처리해야 합니다.
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    
                    // 처리해야 하는 주변 컨테이너의 종류입니다.
                    //      -2 컨테이너 : 현재 제거하는 컨테이너는 가장자리 컨테이너고, 주변에 -2 컨테이너가 있다면 이 또한 가장자리에 포함되므로 -1 로 변경해야 합니다.
                    //      >=0 컨테이너 : 가장자리에 닿았으니, 1 컨테이너로 변경해야 합니다.

                    // 닿음 처리 해야 하는 컨테이너 위치 좌표 큐에 넣습니다.
                    if (0 <= ny && ny < n && 0 <= nx && nx < m && cont_edge[ny][nx] != -1) 
                        setEdgeQ.add(new QNode(ny, nx));
                }
                
                cont_edge[y][x] = -1;
                answer += 1;
            }
            
            // 닿음 처리 해야 하는 컨테이너 위치 좌표 큐를 처리합니다.
            while(!setEdgeQ.isEmpty()) {
                QNode qnow = setEdgeQ.poll();
                if (cont_edge[qnow.y][qnow.x] == -1) continue;

                // 가장자리에 닿은 -2 컨테이너는 -1 컨테이너로 변경한 뒤, noEdgeQ에 넣어 그 컨테이너 주변도 가장자리 포함 처리합니다.
                if (cont_edge[qnow.y][qnow.x] == -2) {
                    cont_edge[qnow.y][qnow.x] = -1;
                    noEdgeQ.add(new QNode(qnow.y, qnow.x));
                }
                else cont_edge[qnow.y][qnow.x] = 1;
            }
            
            // 이후에 가장자리에 포함되어 주변을 가장자리 처리해야 하는 위치 좌표 큐입니다.
            while(!noEdgeQ.isEmpty()) {
                QNode qnow = noEdgeQ.poll();
                if (cont_edge[qnow.y][qnow.x] == -2) continue; // 아직 가장자리에 포함되지 않았다면 넘어갑니다.
                
                // -1 컨테이너가 된 현재 컨테이너 주변을 가장자리 닿음 처리해야합니다. 
                // (-2 컨테이너 였을 때는 주변을 가장자리 닿음 처리하지 않았기 때문입니다.)
                for (int d = 0; d < 4; d++) {
                    int ny = qnow.y + dy[d];
                    int nx = qnow.x + dx[d];
                    
                    if (0 <= ny && ny < n && 0 <= nx && nx < m) {
                        // 현재 컨테이너가 가장자리에 포함되어 있기에, 주변의 -2 컨테이너도 가장자리로 포함시킵니다.
                        if (cont_edge[ny][nx] == -2) {
                            cont_edge[ny][nx] = -1;
                            noEdgeQ.add(new QNode(ny, nx));
                        }
                        
                        // 가장자리에 닿지 않은 컨테이너는 닿았다고 표시해줍니다.
                        if (cont_edge[ny][nx] == 0) cont_edge[ny][nx] = 1;
                    }
                }
            }
        }
        
        // 전체 개수 - 빠진 컨테이너 개수 를 출력합니다.
        return n * m - answer;
    }
}
