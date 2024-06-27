'''
Author    : Lee In Bok
Date      : 2024.06.28(Fri)
Runtime   : 121840 KB
Memory    : 1020 ms (Pypy3)
Algorithm : Implement
'''

import sys
from copy import deepcopy
from collections import deque
inp = sys.stdin.readline

N, Q = map(int, inp().split())
maps = [list(map(int, inp().split(' '))) for _ in range(pow(2, N))]
cmds = list(map(int, inp().split(' ')))
visited = [[False for _ in range(len(maps))] for _ in range(len(maps))]
move = [0, 1, 0, -1]
max_chunk, sums = 0, 0

def rotate(cmd, maps):
  copy_map = deepcopy(maps)
  rb = pow(2, cmd)  # 다음 rotate 기준 좌표(rotate border)
  n = len(maps)

  # i, j 는 회전 강도(L) 에 따른 경계선의 시작을 의미
  # x, y 는 경계선 내부에서 회전하기 위한 인덱스
  for i in range(0, n, rb):
    for j in range(0, n, rb):
      for x in range(rb):
        for y in range(rb):
            maps[i + y][j + rb - 1 - x] = copy_map[i + x][j + y]


def is_valid(x, y, maps):
  ln = len(maps)
  return 0 <= x < ln and 0 <= y < ln

def melt(maps):
  melt_target = []  #  녹여줄 얼음 좌표

  for i in range(len(maps)):
    for j in range(len(maps)):
      cnt = 0

      for idx in range(4):
        next_x = i + move[idx]
        next_y = j + move[idx - 1]

        # 접근 가능하고 얼음이 존재한다면
        if is_valid(next_x, next_y, maps) and maps[next_x][next_y] != 0:
          cnt += 1

      if cnt < 3:  # 3개의 칸 이상의 얼음이 둘러쌓지 않음
        melt_target.append((i, j))

  for x, y in melt_target:
    if maps[x][y] > 0:
      maps[x][y] -= 1

def bfs(srt, visited, maps):
  global max_chunk
  queue = deque([srt])
  chunk = 0
  sums = 0

  while queue:
    x, y = queue.popleft()
    sums += maps[x][y]

    if maps[x][y] != 0:
      chunk += 1

    for idx in range(4):
      next_x = x + move[idx]
      next_y = y + move[idx - 1]

      if is_valid(next_x, next_y, maps) and not visited[next_x][next_y] and maps[next_x][next_y] != 0:
        queue.append((next_x, next_y))
        visited[next_x][next_y] = True

  max_chunk = max(max_chunk, chunk)  # 가장 큰 덩어리
  return sums # 총 합

for cmd in cmds:  # 메인 로직 실행
  rotate(cmd, maps)
  melt(maps)

for i in range(len(maps)):  # 그래프 탐색 시행
    for j in range(len(maps)):
      if not visited[i][j]:
        visited[i][j] = True
        sums += bfs((i, j), visited, maps)

print(sums)
print(max_chunk)

''' 하..... 스트레스
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
'''