'''
Author    : Kim Jae Hwan
Date      : 2024.05.19(Sun)
Runtime   : 40 ms
Memory    : 31120 KB
Algorithm : DP
'''

N = int(input())
T = []
P = []

for _ in range(N):
    t, p = map(int, input().split())
    T.append(t)
    P.append(p)
    
# dp[i]는 i-1일부터 일할때 얻을 수 있는 최대 수익
# N일차와 편하게 비교하기 위해 N+1일차부터 일할때 얻을수 있는 최대 수익 0을 추가했다.
dp = [0] * (N + 1)
ans = 0

# i: 6 5 4 3 2 1 0
for i in range(N-1, -1, -1): 
    # day: i+1일에 일을 시작해서 일 마치는 마지막 날
    # ex. 2일에 일 시작하면 6일까지 일함.
    # day: 1일(i) + 5일(t[i])
    day = i + T[i]

    # 끝마치는 날이 N보다 크다면
    # ex. 7일차에 일을 하면 종료일을 넘는 경우, 7일부터 일할때 최대 수익 dp[6]은 8일차 최대 수익 dp[7] == 0과 같다.
    # ex. 4일차에 일을 하면 종료일을 넘는 경우, 4일부터 일할때 최대 수익 dp[4]는 5일부터 일할때의 최대 수익과 같다.
    if day > N:
        # i일부터 얻을 수 있는 최대 수익 = i일에는 일 못하고 i+1일부터 얻을 수 있는 최대 수익
        dp[i] = dp[i+1]



    # dp[i]: i일부터 얻을 수 있는 최대 수익
    # i일에 일 안하고 i+1일부터 얻을 수 있는 최대 수익 vs i일에 시작한 일로 얻은 수익 + 일 마치는 다음날부터 얻을 수 있는 최대 수익
    else:
        dp[i] = max(dp[i+1], P[i] + dp[day])    # day는 일 마치는 날이므로 dp[day]를 하면 일 마치는 다음날부터 얻을 수 있는 최대 수익이 됨

# 첫날부터 얻을 수 있는 최대 수익
print(dp[0])