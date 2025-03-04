import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-03
 * Runtime          :   73.14 ms
 * Memory           :   108 MB
 * Used algorithm   :   BFS, Implementation
 */
class Solution {

    private int R, C;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, 1, -1};

    private boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private char[][] table;
    private static final char OUTER = '/';
    private static final char EMPTY = ' ';
    private static List<Cord> edgeCords;

    private void init(String[] storage) {
        R = storage.length;
        C = storage[0].length();

        table = new char[R][];
        for (int i = 0; i < R; i++) {
            table[i] = storage[i].toCharArray();
        }

        // 편의를 위해 외곽 좌표들 저장
        edgeCords = new ArrayList<>(2 * R + 2 * C - 4);
        edgeCords.addAll(
                IntStream.range(0, R * C)
                        .filter(cnt -> {
                            int nR = cnt / C, nC = cnt % C;
                            return nR == 0 || nR == R - 1 || nC == 0 || nC == C - 1;
                        })
                        .mapToObj(cnt -> new Cord(cnt / C, cnt % C))
                        .collect(Collectors.toList())
        );
    }

    public int solution(String[] storage, String[] requests) {
        init(storage);

        int answer = R * C;
        for (String request : requests) {

            // 없앨 수 있는 좌표들 return
            List<Cord> removals = getRemovalCords(request);

            answer -= removals.size();

            // 없앤 좌표들 EMPTY 로 채우기
            fillCordsEmpty(removals);

            // OUTER 로 채울 수 있는 거 채우기
            updateOuterBlocks();
        }

        return answer;
    }

    private List<Cord> getRemovalCords(String request) {

        List<Cord> removals = new ArrayList<>();

        final char target = request.charAt(0);
        BiPredicate<Integer, Integer> filter = getFilterOnRequest(request, target);

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (target == table[i][j] && filter.test(i, j)) {
                    removals.add(new Cord(i, j));
                }
            }
        }

        return removals;
    }

    private BiPredicate<Integer, Integer> getFilterOnRequest(String request, char target) {
        // 뭐든지 true
        BiPredicate<Integer, Integer> pass = (r, c) -> true;

        // 외곽 좌표이거나 OUTTER 와 인접해 있으면 true
        BiPredicate<Integer, Integer> outerRegion = (r, c) -> {
            for (int i = 0; i < dr.length; i++) {
                int adjR = r + dr[i];
                int adjC = c + dc[i];

                if (!inRange(adjR, adjC) || table[adjR][adjC] == OUTER) {
                    return true;
                }
            }
            return false;
        };

        return request.length() >= 2 ? pass : outerRegion;
    }

    private void fillCordsEmpty(List<Cord> cords) {
        for (Cord c : cords) {
            table[c.r][c.c] = EMPTY;
        }
    }

    private void updateOuterBlocks() {

        List<Cord> newOuterBlocks = new ArrayList<>();
        boolean[][] visited = new boolean[R][C];

        // 외곽 좌표들로 부터 BFS 탐색
        for (Cord edge : edgeCords) {
            int r = edge.r, c = edge.c;

            if (visited[r][c] || (table[r][c] != OUTER && table[r][c] != EMPTY)) {
                continue;
            }

            // BFS 로 OUTTER 채울 좌표 추가
            newOuterBlocks.addAll(getOuterBlocksWithBFS(r, c, visited));
        }

        // OUTTER 채우기
        for (Cord c : newOuterBlocks) {
            table[c.r][c.c] = OUTER;
        }
    }

    private List<Cord> getOuterBlocksWithBFS(int initR, int initC, boolean[][] visited) {
        List<Cord> outerBlocks = new ArrayList<>();

        visited[initR][initC] = true;

        Queue<Cord> queue = new LinkedList<>();
        queue.add(new Cord(initR, initC));

        while (!queue.isEmpty()) {

            Cord c = queue.poll();
            outerBlocks.add(c);

            for (int i = 0; i < dr.length; i++) {
                int adjR = c.r + dr[i];
                int adjC = c.c + dc[i];

                if (!inRange(adjR, adjC) || (table[adjR][adjC] != OUTER
                        && table[adjR][adjC] != EMPTY) || visited[adjR][adjC]) {
                    continue;
                }

                visited[adjR][adjC] = true;
                queue.add(new Cord(adjR, adjC));
            }
        }

        return outerBlocks;
    }

    @SuppressWarnings("unused")
    private void showTable() {
        for (char[] row : table) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

class Cord {

    final int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Cord{" +
                "r=" + r +
                ", c=" + c +
                '}';
    }
}