def solution(arr):
    n = len(arr) // 2
    minus = 0
    dp = [int(arr[0])]
    for i in range(n):
        num = int(arr[2 * i + 2])
        if arr[2 * i + 1] == '-':
            minus += 1
            if minus % 2:
                dp.append(dp[-1] - num)
            else:
                dp.append(dp[-1] + num)
            for j in range(minus - 1, 0, -1):
                if j % 2:
                    dp[j] = max(dp[j-1] - num, dp[j] + num, dp[j+1])
                else:
                    dp[j] = max(dp[j-1] + num, dp[j] - num, dp[j+1])
            if minus:
                dp[0] = max(dp[0] - num, dp[1])
        else:
            if minus % 2:
                dp[-1] -= num
            else:
                dp[-1] += num
            for j in range(minus - 1, 0, -1):
                if j % 2:
                    dp[j] = max(dp[j] - num, dp[j+1])
                else:
                    dp[j] = max(dp[j] + num, dp[j+1])
            if minus:
                dp[0] = max(dp[0] + num, dp[1])
    answer = max(dp)
    return answer