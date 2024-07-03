import sys
inp = sys.stdin.readline

N, M, K = map(int, inp().split())
maps = [[[] for _ in range(N)] for _ in range(N)]
move = [(-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1), (0, -1), (-1, -1)]
fireballs = []

for _ in range(M):
  r, c, m, s, d = map(int, inp().split())
  fireballs.append((r-1, c-1, m, s, d))

for _ in range(K):
  while fireballs:  # 모든 파이어볼 이동
    r, c, m, s, d = fireballs.pop()

    nx = (r + move[d][0] * s) % N
    ny = (c + move[d][1] * s) % N

    maps[nx][ny].append((m, s, d))

  for i in range(N):
    for j in range(N):
      if maps[i][j]:  # 파이어볼이 존재하는 경우
        sum_mass, sum_speed, even, odd, quantity = 0, 0, 0, 0, len(maps[i][j])

        if quantity == 1:  # 1 개만 존재하는 경우
          m, s, d = maps[i][j].pop()
          fireballs.append((i, j, m, s, d))
          continue
        
        while maps[i][j]:  # step2 진행
          m, s, d = maps[i][j].pop()
          sum_mass += m
          sum_speed += s
          
          if d % 2:
            odd += 1
          else:
            even += 1

        next_mass = sum_mass // 5
        
        if next_mass:  # 파이어볼의 합이 0인 경우 소멸
          next_speed = sum_speed // quantity
          next_directions = [1, 3, 5, 7] if odd == quantity or even == quantity else [0, 2, 4, 6]

          for next_direction in next_directions:
            fireballs.append((i, j, next_mass, next_speed, next_direction))

ans = 0

for r, c, m, s, d in fireballs:
  ans += m

print(ans)