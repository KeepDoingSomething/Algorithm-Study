import sys
from enum import Enum
from collections import deque
from heapq import heappush, heappop, heapify
import math

# 입력 받기
N = int(input().strip())


# 소수 구하기
def get_prime_numbers():
    nums = [1 for _ in range(N + 1)]
    prime_nums = []

    for i in range(2, N + 1):
        for j in range(i * 2, N + 1, i):
            nums[j] = 0

    for i in range(2, len(nums)):
        if nums[i]:
            prime_nums.append(i)

    return prime_nums


def is_within(prime_nums, pointer):
    return pointer < len(prime_nums)


def solution():
    answer = 0

    prime_nums = get_prime_numbers()

    head = 0
    tail = -1
    acc = 0

    while head < len(prime_nums) and tail < len(prime_nums):
        if acc == N:
            answer += 1

        if acc < N:
            tail += 1

            if tail < len(prime_nums):
                acc += prime_nums[tail]
        else:
            acc -= prime_nums[head]
            head += 1

    print(answer)


solution()