import sys
from collections import deque
input = sys.stdin.readline

def is_valid(x, y, m, n):
  return 0 <= x < m and 0 <= y < n

T = int(input())
move = [0, 1, 0, -1]

for _ in range(T):
  M, N, K = map(int, input().split())
  bug_location = set()
  ans = 0

  for _ in range(K):
    x, y = map(int, input().split())

    bug_location.add((x, y))

  while bug_location:  # 전체 농밭에서 1인 경작지
    queue = [bug_location.pop()]
    ans += 1

    while queue:
      cur_x, cur_y = queue.pop()

      for idx in range(4):
        moved_x = cur_x + move[idx]
        moved_y = cur_y + move[idx - 1]
        next_location = (moved_x, moved_y)

        if is_valid(moved_x, moved_y, M, N) and next_location in bug_location:
          bug_location.remove(next_location)
          queue.append(next_location)
  
  print(ans)