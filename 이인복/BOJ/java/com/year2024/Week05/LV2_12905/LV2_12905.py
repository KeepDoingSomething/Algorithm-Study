def solution(board):
    ans, row, col = 0, len(board), len(board[0])
    dp = [[0] * col for _ in range(row)]

    for i in range(row):
        for j in range(col):
            if i == 0 or j == 0:
                dp[i][j] = board[i][j]
            elif board[i][j] == 0:
                dp[i][j] = 0
            else:
                dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
            
            ans = max(dp[i][j], ans)

    return ans * ans

print(solution([[0,1,1,1],[1,1,1,1],[1,1,1,1],[0,0,1,0]]))