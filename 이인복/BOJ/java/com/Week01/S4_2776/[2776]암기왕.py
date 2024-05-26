'''
Author    : Lee In Bok
Date      : 2024.05.13(Mon)
Runtime   : 215396 KB
Memory    : 1620 ms
Algorithm : Hash
'''

import sys
inp = sys.stdin.readline

T = int(inp())

for _ in range(T):
  N = int(inp())
  note1 = set(map(int, inp().split(' ')))
  M = int(inp())

  for num in list(map(int, inp().split(' '))):
    if num in note1:
      print(1)
    else:
      print(0)