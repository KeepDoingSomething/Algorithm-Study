from math import inf

N = int(input().strip())

colors = [[0, 0, 0] for _ in range(N)]

for i in range(N):
    R, G, B = map(int, input().split())

    colors[i][0] = R
    colors[i][1] = G
    colors[i][2] = B


def iterate_dp(dp):
    i = 1
    while i < N:
        dp[i][0] = min(dp[i - 1][1], dp[i - 1][2]) + colors[i][0]
        dp[i][1] = min(dp[i - 1][0], dp[i - 1][2]) + colors[i][1]
        dp[i][2] = min(dp[i - 1][0], dp[i - 1][1]) + colors[i][2]

        i += 1


def get_min_price_red():
    dp = [[inf for _ in range(3)] for _ in range(N)]

    # 0 = red, 1 = green, 2 = blue
    dp[0][0] = colors[0][0]

    iterate_dp(dp)

    return min(dp[N - 1][1], dp[N - 1][2])


def get_min_price_green():
    dp = [[inf for _ in range(3)] for _ in range(N)]

    # 0 = red, 1 = green, 2 = blue
    dp[0][1] = colors[0][1]

    iterate_dp(dp)

    return min(dp[N - 1][0], dp[N - 1][2])


def get_min_price_blue():
    dp = [[inf for _ in range(3)] for _ in range(N)]

    # 0 = red, 1 = green, 2 = blue
    dp[0][2] = colors[0][2]

    iterate_dp(dp)

    return min(dp[N - 1][0], dp[N - 1][1])


def main():
    min_price = min(get_min_price_red(), get_min_price_green(), get_min_price_blue())

    print(min_price)


main()
