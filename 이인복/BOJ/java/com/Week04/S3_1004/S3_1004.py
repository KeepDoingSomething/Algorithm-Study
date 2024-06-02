import sys
inp = sys.stdin.readline

T = int(inp())

for _ in range(T):
  x1, y1, x2, y2 = map(int, inp().split(' '))
  cnt = 0
  N = int(inp())

  for _ in range(N):
    cx, cy, r = map(int, inp().split(' '))

    # (x - cx)^2 + (y - cy)^2 = r2 를 사용
    dist1 = (x1 - cx) ** 2 + (y1 - cy) ** 2
    dist2 = (x2 - cx) ** 2 + (y2 - cy) ** 2
    pow_r = r ** 2

    # 내부 && 외부 || 외부 && 내부
    if (dist1 < pow_r and dist2 > pow_r) or (dist1 > pow_r and dist2 < pow_r):
      cnt += 1

  print(cnt)