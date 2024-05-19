"""
Author    : Jang Woo Jin
Date      : 2024.05.19(Sun)
Runtime   : 31552 KB
Memory    : 108 ms
Algorithm : Data Structure, Stack
"""
import sys
input = sys.stdin.readline

N = int(input())

answer = 0
words = []
for _ in range(N):
    word = input().strip()
    stack = []
    for w in word:
        if not stack:
            stack.append(w)
        elif stack[-1] == w:
            stack.pop()
        else:
            stack.append(w)
    if not stack:
        answer += 1
print(answer)