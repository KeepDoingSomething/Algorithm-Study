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