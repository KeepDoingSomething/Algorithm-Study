'''
Author    : Lee In Bok
Date      : 2024.06.20(Thu)
Runtime   : 87136 KB
Memory    : 1428 ms
Algorithm : Heap
'''

import sys
from heapq import heappop, heappush
inp = sys.stdin.readline

N ,K = map(int, inp().split())
gems = []

for _ in range(N):  # 보석 (무게, 가치) 입력
  heappush(gems, tuple(map(int, inp().split())))

bags = [int(inp()) for _ in range(K)]  # 가방 수용 무게 입력
bags.sort()

ans = 0
tmp_bags = []

for bag in bags:  # 가방의 수용 가능 무게
  while gems:
    if bag >= gems[0][0]:
      heappush(tmp_bags, -heappop(gems)[1])
    else:
      break
    
  if tmp_bags:
    ans += -heappop(tmp_bags)

print(ans)