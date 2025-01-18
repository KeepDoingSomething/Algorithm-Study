'''
Author    : Lee In Bok
Date      : 2024.05.13(Mon)
Runtime   : 34008 KB
Memory    : 64 ms
Algorithm : Queue
'''

from collections import deque

N, K = map(int, input().split())
dQueue, res = deque([idx for idx in range(1, N + 1)]), []

while dQueue:
  dQueue.rotate(-(K-1))
  res.append(str(dQueue.popleft()))

print('<' + ', '.join(res) + '>')