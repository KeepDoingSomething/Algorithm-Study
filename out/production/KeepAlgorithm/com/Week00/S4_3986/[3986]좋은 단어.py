'''
Author    : Lee In Bok
Date      : 2024.05.13(Mon)
Runtime   : 31552 KB
Memory    : 108 ms
Algorithm : Stack
'''

import sys
inp = sys.stdin.readline
N = int(inp())
res = 0

for _ in range(N):
  word = inp().strip()
  stack = []

  for letter in word:
    if stack:
      if stack[-1] == letter:
        stack.pop()
      else:
        stack.append(letter)
    else:
      stack.append(letter)

  if not stack:
    res += 1

print(res)