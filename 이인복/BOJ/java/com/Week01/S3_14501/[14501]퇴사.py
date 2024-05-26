'''
Author    : Lee In Bok
Date      : 2024.05.14(Tue)
Runtime   : 31120 KB
Memory    : 52 ms
Algorithm : Brute Force
'''

import sys
inp = sys.stdin.readline

N = int(inp())
days, costs = [], []
ans = 0

for _ in range(N):
  day, cost = map(int, inp().split(' '))
  days.append(day)
  costs.append(cost)

def sol(day, sum):
  global ans

  if day >= N:
    ans = max(ans, sum)
    return
  
  next_day = day + days[day]

  if next_day <= N:
    sol(next_day, sum + costs[day])
  else:
    sol(next_day, sum)
  sol(day + 1, sum)

sol(0, 0)
print(ans)