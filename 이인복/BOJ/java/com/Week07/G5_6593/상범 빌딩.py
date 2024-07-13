'''
Author    : Lee In Bok
Date      : 2024.06.25(Mon)
Runtime   : 34088 KB
Memory    : 192 ms
Algorithm : BFS
'''

import sys
from collections import deque
inp = sys.stdin.readline

move = [0, 0, 1, 0, 0, -1]
STRAT = 'S'
WALL = '#'
PATH = '.'
END = 'E'

while True:
  L, R, C = map(int, inp().split())

  if L == 0 and R == 0 and C == 0:
    break

  building = [[] for _ in range(L)]
  srt = None

  for l in range(L):
    for r in range(R):
      row = list(inp().strip())

      if STRAT in row:
        c = row.index(STRAT)
        row[c] = WALL  # 시작 지점 방문 처리
        srt = (l, r, c, 1)  # 시작 좌표

      building[l].append(row)
    inp()  # 중간 공백라인 제거

  # BFS 로직 시작
  queue = deque([srt])
  finish = False
  
  while queue:
    cur_l, cur_r, cur_c, time = queue.popleft()

    for idx in range(6):
      next_l = cur_l + move[idx]
      next_r = cur_r + move[idx - 1]
      next_c = cur_c + move[idx - 2]

      # 좌표가 유효하며 지나갈 수 있는 길인지 체크
      if 0 <= next_l < L and 0 <= next_r < R and 0 <= next_c < C and building[next_l][next_r][next_c] != WALL:
        if building[next_l][next_r][next_c] == END:
          finish = True
          print(f'Escaped in {time} minute(s).')
        
        building[next_l][next_r][next_c] = WALL
        queue.append((next_l, next_r, next_c, time + 1))

  if not finish:
    print('Trapped!')