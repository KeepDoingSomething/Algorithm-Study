import sys
input = sys.stdin.readline

N, K = map(int, input().split())

# 리스트
l = [i for i in range(1, N+1)]

# 결과 리스트
result = []

seq = 0
for i in range(1, N+1):
    seq += K - 1
    if seq >= len(l):
        seq %= len(l)
    result.append(l.pop(seq))
print("<" + ", ".join(map(str, result)) + ">")