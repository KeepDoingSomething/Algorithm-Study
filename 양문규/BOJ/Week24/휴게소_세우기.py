import sys
from enum import Enum
from collections import deque
from heapq import heappush, heappop, heapify
import math

# 입력 받기
N, M, L = map(int, input().split())

# 입력 받기
# 고속도로의 시작과 끝점을 먼저 추가한 채로 휴게소의 위치를 받음
unsorted_stops = [0, L]
line = list(map(int, input().split()))
for stop in line:
    unsorted_stops.append(stop)

# 휴게소를 정렬하고 시작하기
stops = sorted(unsorted_stops)

# 각 휴게소 사이의 거리 배열
distances = []
for i in range(0, len(stops) - 1):
    distances.append(abs(stops[i] - stops[i + 1]))


def get_rest_stop_count(mid):
    # 설치해야 하는 휴게소 개수
    count = 0

    for dist in distances:
        # 각 휴게소 간 간격이 휴게소 사이의 평균 목표 거리보다 클 경우,
        # 이 곳엔 신규 휴게소가 몇 개 세워질 수 있는지 구함
        if dist >= mid:
            # dist - 1을 해야하는 이유는, 소수점으로 나누어떨어질 때, 휴게소의 개수가 정확하게 계산되지 않을 경우...
            # 사실 잘 이해 못함
            count += (dist - 1) // mid

    return count


def solution():
    # 거리의 최대 값 구하기
    max_dist = max(distances)

    low = 1
    high = max_dist
    while low < high:
        # 최소 거리와 최대 거리의 중간 지점을 구함
        # 즉, 각 휴게소 간 거리가 "평균적으로" 이정도로 작아져야 한는 것을 의미
        mid = (low + high) // 2

        # 설치해야하는 휴게소 수
        count = get_rest_stop_count(mid)

        # 설치해야하는 휴게소가 M보다 같거나 작으면 설치 여유분이 남는다는 뜻이므로, 최대 간격을 더 작게 설정할 수 있다.
        if count <= M:
            high = mid
        # 설치해야하는 휴게소가 M보다 크면 설치 여유분이 없다는 뜻으로, 최대 간격을 더 크게 설정해야 한다.
        else:
            low = mid + 1

    return low


print(solution())