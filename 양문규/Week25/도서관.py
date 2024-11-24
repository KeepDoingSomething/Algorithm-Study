import sys
from enum import Enum
from collections import deque
from heapq import heappush, heappop, heapify
import math


# 가장 먼 거리
max_dist = 0

# 음수 저장
list_left = []
# 양수 저장
list_right = []

# 입력 받기
N, M = map(int, input().split())

line = list(map(int, input().split()))
for n in line:
    if n < 0:
        # 절대값을 적용한 값을 넣음)
        list_left.append(abs(n))
        max_dist = max(max_dist, abs(n))
    else:
        list_right.append(n)
        max_dist = max(max_dist, n)

# 내림차순으로 정렬
list_left.sort()
list_right.sort()


def return_books_left():
    answer = list_left[-1] * 2

    i = M
    while i > 0 and len(list_left):
        list_left.pop()
        i -= 1

    return answer


def return_books_right():
    answer = list_right[-1] * 2

    i = M
    while i > 0 and len(list_right):
        list_right.pop()
        i -= 1

    return answer


def solution():
    answer = 0

    while len(list_left) and len(list_right):
        # 가장 먼 거리에 있는 책부터 가져다 놓음
        if list_left[-1] > list_right[-1]:
            answer += return_books_left()
        else:
            answer += return_books_right()

    # 한쪽 방향으로 남은 책을 가져다놓기
    while len(list_left):
        answer += return_books_left()

    while len(list_right):
        answer += return_books_right()

    # 마지막은 왕복 거리를 계산하지 않는다하였으므로 최대 거리를 빼주기
    answer -= max_dist

    print(answer)


solution()