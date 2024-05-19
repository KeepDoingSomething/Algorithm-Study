"""
Author    : Jang Woo Jin
Date      : 2024.05.19(Sun)
Runtime   : 31120 KB
Memory    : 44 ms
Algorithm : Dynamic Programming
"""
import sys
input = sys.stdin.readline

N = int(input())
T, P, dp = [], [], []
for _ in range(N):
    _T, _P = map(int, input().split())
    T.append(_T)
    P.append(_P)
    dp.append(_P)

dp.append(0)
for i in range(N-1, -1, -1):
    # 퇴사까지 남은 시간동안 일을 할 수 있어야만 함
    # 퇴사일 이후까지 처리해야한다면?
    if i + T[i] > N:
        dp[i] = dp[i+1]
    else:
        dp[i] = max(dp[i+1], dp[i + T[i]] + P[i])
print(dp[0])