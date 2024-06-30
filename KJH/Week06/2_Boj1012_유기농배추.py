import sys
sys.setrecursionlimit(10**6)

def dfs(x, y):
    next = [(1,0), (-1,0), (0,1), (0,-1)]
    
    for i in range(len(next)):
        nextX = x + next[i][0]
        nextY = y + next[i][1]
        if (0<= nextX < M) and (0<= nextY < N) and grid[nextY][nextX] == 1:
            grid[nextY][nextX] = 2
            dfs(nextX, nextY)
    
T = int(input())

for _ in range(T):
    M, N, K = map(int, input().split())
    grid = [[0 for _ in range(M)] for _ in range(N)]
    
    for _ in range(K):
        X, Y = map(int, input().split())
        grid[Y][X] = 1
    
    ans = 0
    for y in range(N):
        for x in range(M):
            if grid[y][x] == 1:
                dfs(x, y)
                ans += 1
    print(ans)
            