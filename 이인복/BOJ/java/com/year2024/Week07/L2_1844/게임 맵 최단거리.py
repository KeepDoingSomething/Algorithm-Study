from collections import deque

def solution(maps):
    move = [0, 1, 0, -1]
    n, m = len(maps), len(maps[0])
    queue = deque([(0, 0, 1)])  # x, y, distance

    while queue:
        cur_x, cur_y, cur_dist = queue.popleft()

        if cur_x == (n - 1) and cur_y == (m - 1):  # 마지막 좌표에 도착 했을 때
            return cur_dist

        for idx in range(4):
            next_x = cur_x + move[idx]
            next_y = cur_y + move[idx - 1]

            if 0 <= next_x < n and 0 <= next_y < m and maps[next_x][next_y] == 1:
                maps[next_x][next_y] = 0
                queue.append((next_x, next_y, cur_dist + 1))

    return -1

print(solution([[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]))