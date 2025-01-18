'''
Author    : Lee In Bok
Date      : 2024.06.09(Sun)
Runtime   : 52540 KB
Memory    : 432 ms
Algorithm : Prim
'''

''' 1차 풀이 - 프림 알고리즘
import sys
from heapq import heappop, heappush

inp = sys.stdin.readline

N = int(inp())  # 노드 개수
M = int(inp())  # 간선 개수
graph = [[0] * (N + 1) for _ in range(N + 1)]

for _ in range(M):
  x, y, dist = map(int, inp().split())
  graph[x][y] = graph[y][x] = dist

def prim():
  heap = []
  ans = 0
  visited = [False] * (N + 1)

  heappush(heap, (0, 1))  # 시작점

  while heap:
    dist, node = heappop(heap)

    if not visited[node]:
      visited[node] = True
      ans += dist

      for i in range(1, N + 1):
        if graph[node][i] != 0 and not visited[i]:
          heappush(heap, (graph[node][i], i))

  return ans

print(prim())
'''