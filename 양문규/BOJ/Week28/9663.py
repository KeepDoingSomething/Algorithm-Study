import sys
from enum import Enum
from collections import deque
from heapq import heappush, heappop, heapify
import math

# 입력 받기
N = int(input().strip())

vx = [-1] * N
vy = [-1] * N


def back_tracking(r, c):
    """
    r: row
    c: column
    """
    global vx, vy

    # 현재 행까지만 겹치는 것이 있는지 확인
    for i in range(r):
        # 가로 겹침 및 세로 겹침
        if c == vx[i] or r == vy[i]:
            return 0

        # 대각 겹침
        if abs(c - vx[i]) == abs(r - vy[i]):
            return 0

    # 종료 조건
    if r == N - 1:
        return 1

    # 종료되지 않은 경우 퀸을 배치할 수 있음
    vx[r] = c
    vy[r] = r

    # 다음 행으로 건너가기
    answer = 0
    for i in range(N):
        answer += back_tracking(r + 1, i)

    return answer


def solution():
    answer = 0

    for i in range(N):
        answer += back_tracking(0, i)

    return answer


print(solution())
