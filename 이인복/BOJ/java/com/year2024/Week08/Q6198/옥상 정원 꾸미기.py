'''
Author    : Lee In Bok
Date      : 2024.07.02(Tue)
Runtime   : 42052 KB
Memory    : 1980 ms
Algorithm : Stack
'''

N = int(input())
stack = []
ans = 0

for idx in range(N):
  num = int(input())

  while stack and stack[-1][1] <= num:
    stack.pop()

  stack.append((idx, num))
  ans += len(stack) - 1

print(ans)