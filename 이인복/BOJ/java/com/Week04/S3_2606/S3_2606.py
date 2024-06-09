'''
Author    : Lee In Bok
Date      : 2024.06.02(Sun)
Runtime   : 31120 KB
Memory    : 40 ms
Algorithm : DFS
'''

import sys
inp = sys.stdin.readline

N = int(inp())  # 컴퓨터 개수
M = int(inp())  # 연결된 쌍
visited = [False] * (N + 1)
graph = [[] for _ in range(N + 1)]

for _ in range(M):
  x, y = map(int, inp().split(' '))

  graph[x].append(y)
  graph[y].append(x)

def dfs(cur_node: int):
  for next_node in graph[cur_node]:
    if not visited[next_node]:
      visited[next_node] = True
      dfs(next_node)

dfs(1)

sums = sum(visited)
print(0 if sums == 0 else sums - 1)