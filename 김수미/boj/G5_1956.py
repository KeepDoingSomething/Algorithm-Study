'''
 * Author    : Kim Su Mi
 * Date      : 2024.09.28(SAT)
 * Runtime   : 764 ms
 * Memory    : 111912 KB
 * Algorithm : Floyd-Warshall
'''

import sys
v, e = map(int, sys.stdin.readline().split())

# 방향성 있는 그래프에서 최단거리 구하기
graph = [[int(1e9)] * (v+1) for _ in range(v+1)]

# 도로의 길이를 값으로 넣기
for _ in range(e):
    a, b, c = map(int, sys.stdin.readline().split())
    graph[a][b] = c

# 플로이드 와샬 : 모든 노드에서 다른 모든 노드까지의 최단경로 계산
for i in range(1, v+1):
    for a in range(1, v+1):
        for b in range(1, v+1):
            graph[a][b] = min(graph[a][b], graph[a][i] + graph[i][b])

# 자기 자신마을에서 자기 자신마을으로 돌아오는 거리 중 최소값 찾기
result = int(1e9)
for i in range(1, v+1):
    result = min(result, graph[i][i])

if result == int(1e9):
    print(-1)
else:
    print(result)
