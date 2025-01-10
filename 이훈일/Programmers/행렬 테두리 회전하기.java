class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        int[][] graph =  new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                graph[i][j] = num;
                num++;
            }
        }
        
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            answer[i] = Integer.MAX_VALUE;
        }
        
        for(int i = 0; i < queries.length; i++) {
            int fx = queries[i][0] - 1;
            int fy = queries[i][1] - 1;
            int ex = queries[i][2] - 1;
            int ey = queries[i][3] - 1;
            
            int[] point = new int[]{fx, fy};
            int pre = graph[fx+1][fy];
            int cur = 0;
            for(int dir = 0; dir < 4; dir++) {
                if(dir == 0) {
                    while(point[1] < ey) {
                        cur = graph[point[0]][point[1]];
                        graph[point[0]][point[1]] = pre;
                        answer[i] = Math.min(answer[i], cur);
                        point[0] += dx[dir];
                        point[1] += dy[dir];
                        pre = cur;
                    }
                } else if(dir == 1) {
                    while(point[0] < ex) {
                        cur = graph[point[0]][point[1]];
                        graph[point[0]][point[1]] = pre;
                        answer[i] = Math.min(answer[i], cur);
                        point[0] += dx[dir];
                        point[1] += dy[dir];
                        pre = cur;
                    }
                } else if(dir == 2) {
                    while(point[1] > fy) {
                        cur = graph[point[0]][point[1]];
                        graph[point[0]][point[1]] = pre;
                        answer[i] = Math.min(answer[i], cur);
                        point[0] += dx[dir];
                        point[1] += dy[dir];
                        pre = cur;
                    }
                } else if(dir == 3) {
                    while(point[0] > fx) {
                        cur = graph[point[0]][point[1]];
                        graph[point[0]][point[1]] = pre;
                        answer[i] = Math.min(answer[i], cur);
                        point[0] += dx[dir];
                        point[1] += dy[dir];
                        pre = cur;
                    }
                }
            }
        }
        
        return answer;
    }
}
