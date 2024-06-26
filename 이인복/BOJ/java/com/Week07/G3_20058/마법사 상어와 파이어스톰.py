import sys
from copy import deepcopy
from collections import deque
inp = sys.stdin.readline

N, Q = map(int, inp().split())
maps = [list(map(int, inp().split(' '))) for _ in range(pow(2, N))]
cmds = list(map(int, inp().split(' ')))
visited = [[False for _ in range(len(maps))] for _ in range(len(maps))]
move = [0, 1, 0, -1]
max_value, sums = 0, 0

def rotate(cmd, maps):
  copy_map = deepcopy(maps)
  rb = pow(2, cmd)  # 다음 rotate 기준 좌표(rotate border)
  sb = 0 if cmd - 2 < 0 else pow(2, (cmd - 2)) # rotate 이루어지는 좌표(small border)
  rotate_move = [rb // 2, 0, -(rb // 2), 0]

  for i in range(0, len(maps), rb):
    for j in range(0, len(maps), rb):
      for k in range(i, i + pow(2, sb)):
        for l in range(j, j + pow(2, sb)):
          cur_x = k
          cur_y = l

          for idx in range(4):
            next_x = cur_x + rotate_move[idx - 1]
            next_y = cur_y + rotate_move[idx]

            maps[next_x][next_y] = copy_map[cur_x][cur_y]
            cur_x = next_x
            cur_y = next_y


def is_valid(x, y, maps):
  ln = len(maps)
  return 0 <= x < ln and 0 <= y < ln

def melt(maps):
  melt_target = []

  for i in range(len(maps)):
    for j in range(len(maps)):
      cnt = 0

      for idx in range(4):
        next_x = i + move[idx]
        next_y = j + move[idx - 1]

        if is_valid(next_x, next_y, maps) and maps[next_x][next_y] != 0:
          cnt += 1

      if cnt < 3:
        melt_target.append((i, j))

  for x, y in melt_target:
    if maps[x][y] > 0:
      maps[x][y] -= 1

def bfs(srt, visited, maps):
  global max_value
  queue = deque([srt])
  chunk = 0
  sums = 0

  while queue:
    x, y = queue.popleft()
    sums += maps[x][y]
    chunk += 1

    for idx in range(4):
      next_x = x + move[idx]
      next_y = y + move[idx - 1]

      if is_valid(next_x, next_y, maps) and not visited[next_x][next_y]:
        queue.append((next_x, next_y))
        visited[next_x][next_y] = True

  max_value = max(max_value, chunk)  # 가장 큰 덩어리
  return sums

for cmd in cmds:
  if cmd != 0:
    rotate(cmd, maps)
    melt(maps)

for i in range(len(maps)):
    for j in range(len(maps)):
      if not visited[i][j]:
        visited[i][j] = True
        sums += bfs((i, j), visited, maps)

print(sums)
print(max_value)