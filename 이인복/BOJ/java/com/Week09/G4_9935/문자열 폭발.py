'''
Author    : Lee In Bok
Date      : 2024.07.08(Mon)
Runtime   : 49136 KB
Memory    : 624 ms
Algorithm : Stack
'''

strs = list(input())
bomb = list(input())
bomb_len = len(bomb)
stack = []

for c in strs:
  stack.append(c)

  while len(stack) >= bomb_len and stack[-bomb_len:] == bomb:
    for _ in range(bomb_len):
      stack.pop()

print(''.join(stack) if stack else 'FRULA')