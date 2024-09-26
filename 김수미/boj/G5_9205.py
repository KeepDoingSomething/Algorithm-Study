''' 
* Author    : Kim Su Mi
 * Date      : 2024.09.26(Tue)
 * Runtime   : 52 ms
 * Memory    : 34088 KB
 * Algorithm : BFS
'''

import sys
from collections import deque 

def bfs():
    queue = deque()
    queue.append((home_x, home_y))

    while queue:
        # 편의점을 들리지않고, 바로 페스티벌로 갈 수 있다면
        x, y = queue.popleft()
        if abs((festival_x - x)) + abs((festival_y - y)) <= 1000:
            print("happy")
            return

        # 편의점을 들러야한다면 모든 편의점 방문한 후
        for i in range(n):
            # 그 편의점을 방문하지않았다면
            if not visited[i]:
                new_x, new_y = convenience_stores[i]
                if abs((new_x - x)) + abs((new_y - y)) <= 1000:
                    visited[i] = 1
                    queue.append((new_x, new_y))

    print("sad")
    return

t = int(sys.stdin.readline())

for _ in range(t):
    n = int(sys.stdin.readline())
    convenience_stores = []

    # 시작 지점은 정해져있음
    home_x, home_y = map(int, sys.stdin.readline().split())
    for _ in range(n):
        convenience_stores.append(tuple(map(int, sys.stdin.readline().split())))

    festival_x, festival_y = map(int, sys.stdin.readline().split())

    # 방문한 편의점 확인
    visited = [0 for _ in range(n+1)]
    bfs()

