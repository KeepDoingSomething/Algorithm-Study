/**
 * Author    : HojipKim
 * Date      : 2024.06.26(Wed)
 * Runtime   : 11.53 ms
 * Memory    : 53.5 MB
 * Algorithm : 깊이／너비우선탐색（DFS／BFS）
 */
class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Solution {
    public static int[][] directions = new int[][]{{1,0}, {0, 1}, {-1,0}, {0,-1}};
    Deque<Node> deque = new ArrayDeque<>();
    public static int[][] isVisited;

    public int solution(int[][] maps){

        int y_length =maps.length;
        int x_length = maps[0].length;

        isVisited = new int[y_length][x_length];

        Node node = new Node(0, 0);

        return bfs(node, maps, x_length-1, y_length-1);
    }

    public int bfs(Node node, int[][] maps, int n, int m){
        boolean isFind = false;

        isVisited[node.getY()][node.getX()] = 1;

        deque.offer(node);

        while(!deque.isEmpty()){
            Node cur = deque.poll();
            int dx = cur.getX();
            int dy = cur.getY();

            if(dx == n && dy == m){
                isFind = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx + directions[i][0];
                int ny = dy + directions[i][1];

                if(((0 <= nx) && (nx < maps[0].length) && (0 <= ny) && (ny < maps.length))) {
                    if(isVisited[ny][nx] == 0 && maps[ny][nx] == 1) {
                        isVisited[ny][nx] = isVisited[dy][dx] + 1;
                        Node newNode = new Node(nx, ny);
                        deque.offer(newNode);
                    }
                }
            }
        }

        return isFind ? isVisited[m][n] : -1;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.solution(new int[][]{{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}});
        System.out.println("ans : " + ans);
    }
}