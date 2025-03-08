import java.util.*;

class Solution {
    static int N, M;
    static String[][] graph;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static int solution(String[] storage, String[] requests) {        
        N = storage.length;
        M = storage[0].length();
        graph = new String[N][M];
        for(int i = 0; i < N; i++) {
            String[] line = storage[i].split("");
            for(int j = 0; j < M; j++) {
                graph[i][j] = line[j];
            }
        }
        
        for(String request : requests) {
            String word = String.valueOf(request.charAt(0));
            if(request.length() > 1) {
                putNodesUp(word);
                changeOne();
            } else {
                putNodesUnder(word);
                changeZero();
            }
        }
        
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!graph[i][j].equals("0") && !graph[i][j].equals("1")) {                  
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public static void putNodesUnder(String word) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(graph[i][j].equals(word)) {
                    if(getQuoin(i, j)) {
                        queue.add(new int[]{i, j});
                        oneChangeZero(i, j);
                    }
                }
            }
        }
    }
    
    public static void changeZero() {
        int size = queue.size();
        for(int i = 0; i < size; i++) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            graph[x][y] = "0";
        }
    }
    
    public static void putNodesUp(String word) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(graph[i][j].equals(word)) {
                    graph[i][j] = "0";
                    queue.add(new int[]{i, j});
                }
            }
        }
    }
    
    public static void changeOne() {
        int size = queue.size();
        for(int i = 0; i < size; i++) {
            int[] node = queue.poll();
            int x = node[0];
            int y = node[1];
            
            if(!getQuoin(x, y)) {
                graph[x][y] = "1";
            }
        }
    }
    
    public static boolean getQuoin(int x, int y) {
        for(int j = 0; j < 4; j++) {
            int nx = x + dx[j];
            int ny = y + dy[j];
               
            if(nx < 0 || nx >= N || ny < 0 || ny >= M || graph[nx][ny].equals("0")) {
                return true;
            }
        }
        
        return false;
    }

    public static void oneChangeZero(int x, int y) {
        for(int j = 0; j < 4; j++) {
            int nx = x + dx[j];
            int ny = y + dy[j];
               
            if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if(graph[nx][ny].equals("1")) {
                    queue.add(new int[]{nx, ny});  
                    break;
                }
            }
        }        
    }
}
